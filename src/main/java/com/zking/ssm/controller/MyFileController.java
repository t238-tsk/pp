package com.zking.ssm.controller;

import com.zking.ssm.dto.MyFileDto;
import com.zking.ssm.model.Book;
import com.zking.ssm.model.MyFile;
import com.zking.ssm.service.IBookService;
import com.zking.ssm.service.IMyFileService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@Controller
@RequestMapping(value = "myFile")
public class MyFileController {
    @Autowired
    private IBookService bookService;
    @Autowired
    private IMyFileService myFileService;
    @RequestMapping(value = "/toUpload")
    public String toUpload(){
        System.out.println("toUpload.......");
        return "book/uploadbook";//逻辑视图名
    }

    @RequestMapping(value = "/upload")
    public String upload(MyFileDto myFileDto,HttpServletRequest request){
        System.out.println("upload.......");
        Integer bookId = myFileDto.getBookId();
        MultipartFile file = myFileDto.getFile();
        System.out.println(bookId);
        System.out.println(file);
        String targetPath="/upload"+ File.separator+file.getOriginalFilename();
        String realPath = request.getServletContext().getRealPath(targetPath);

        try {
            file.transferTo(new File(realPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //1.新增一条文件上传的记录
        MyFile myFile=new MyFile();
        long fileId = System.currentTimeMillis();
        myFile.setFileId(fileId);
        myFile.setRealName(file.getOriginalFilename());
        myFile.setContentType(file.getContentType());
        myFile.setFilePath(targetPath);
        //2.修改书本表的img字段
        Book book = new Book();
        book.setBookId(bookId);
        book.setImg(fileId);
        bookService.updImgs(book,myFile);

        return "redirect:/hello";//逻辑视图名
     }

    @RequestMapping(value="/download")
    public ResponseEntity<byte[]> download(@RequestParam Long fileId,HttpServletRequest request){

        //先根据文件id查询对应图片信息
        MyFile myFile = myFileService.selectByPrimaryKey(fileId);
        String targetPath=myFile.getFilePath();
        String realPath = request.getServletContext().getRealPath(targetPath);


        //下载关键代码
        File file=new File(realPath);//真实路径
        HttpHeaders headers = new HttpHeaders();//http头信息
        String downloadFileName = null;
        try{
             downloadFileName = new String(myFile.getRealName().getBytes("UTF-8"),"iso-8859-1");//设置编码

        }catch (Exception e){
            e.printStackTrace();
        }
        if(null!=downloadFileName){
            System.out.println("123");
            headers.setContentDispositionFormData("attachment", downloadFileName);
        }else{
            System.out.println("12344");
            headers.setContentDispositionFormData("attachment", myFile.getRealName());
        }
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //MediaType:互联网媒介类型  contentType：具体请求中的媒体类型信息
        try {
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    //多文件上传
    @RequestMapping(value = "/uploads")
    public String uploads(MultipartFile[] myFileDto,HttpServletRequest request){
        System.out.println("uploads.......");

        String uploadFileName = ""; //上传的文件名
        String fieldName = "";  //表单字段元素的name属性值

        //请求信息中的内容是否是multipart类型
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        //上传文件的存储路径（服务器文件系统上的绝对文件路径）
        String uploadFilePath = request.getSession().getServletContext().getRealPath("upload/" );
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                //解析form表单中所有文件
                List<FileItem> items = upload.parseRequest(request);
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {   //依次处理每个文件
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()){  //普通表单字段
                        fieldName = item.getFieldName();   //表单字段的name属性值
                        if (fieldName.equals("user")){
                            //输出表单字段的值
                            System.out.print(item.getString("UTF-8")+"上传了文件。<br/>");
                        }
                    }else{  //文件表单字段
                        String fileName = item.getName();
                        if (fileName != null && !fileName.equals("")) {
                            File fullFile = new File(item.getName());
                            File saveFile = new File(uploadFilePath, fullFile.getName());
                            item.write(saveFile);
                            uploadFileName = fullFile.getName();
                            System.out.print("上传成功后的文件名是："+uploadFileName);
                            System.out.print("\t\t下载链接:"+"<a href='download.action?name="+uploadFileName+"'>"+uploadFileName+"</a>");
                            System.out.print("<br/>");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/uploads";//逻辑视图名
    }


    //多文件下载
    @RequestMapping(value="/downloads")
    public ResponseEntity<byte[]> downloads(HttpServletRequest request, HttpServletResponse response, @RequestParam("name") String filename)throws Exception {

        //下载显示的文件名，解决中文名称乱码问题
        filename=new String(filename.getBytes("iso-8859-1"),"UTF-8");
        //下载文件路径
        String path = request.getServletContext().getRealPath("/upload/");
        File file = new File(path + File.separator + filename);
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = new String(filename.getBytes("iso-8859-1"),"UTF-8");
        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("Content-Disposition", downloadFielName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }



}

