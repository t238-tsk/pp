package com.zking.ssm.model;

import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@ToString
public class Book {
    // 书本验证分组
    public static interface ValidateGroups {
        // 新增
        public static interface Add {
        }

        // 修改
        public static interface Edit {
        }
        //删除
        public static interface DeleteOne {
        }
        //查询单个
        public static interface Single {
        }
    }
    @NotNull(message = "书本编号不能为空",groups = {ValidateGroups.Edit.class,
            ValidateGroups.DeleteOne.class,ValidateGroups.Single.class
    })
    private Integer bookId;

    @NotBlank(message = "书本名字不能为空",groups = {ValidateGroups.Add.class,ValidateGroups.Edit.class})
    @Size(min = 2,max = 20,message = "书本名字必须再2-20之间",groups = {ValidateGroups.Add.class,ValidateGroups.Edit.class})
    private String bookName;

    @NotNull(message = "书本价格不能为空",groups = {ValidateGroups.Add.class,ValidateGroups.Edit.class})
    @Range(min = 20,max = 300,message = "书本价格必须在20-300之间",groups = {ValidateGroups.Add.class,ValidateGroups.Edit.class})
    private Float price;

    private Long img;

    private Date myDate;

    public Book(Integer bookId, String bookName, Float price, Long img) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
        this.img = img;
    }

    public Book() {
        super();
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Long getImg() {
        return img;
    }

    public void setImg(Long img) {
        this.img = img;
    }

    public Date getMyDate() {
        return myDate;
    }

    public void setMyDate(Date myDate) {
        this.myDate = myDate;
    }
}