package com.techthinker.CAWeb.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Question entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="question"
    ,catalog="campusassistant_web"
)

public class Question  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -6116015545219419257L;
	private Integer questionId;
     private User user;
     private String content;
     private String answer;
     private Boolean checked;


    // Constructors

    /** default constructor */
    public Question() {
    }

    
    /** full constructor */
    public Question(User user, String content, String answer, Boolean checked) {
        this.user = user;
        this.content = content;
        this.answer = answer;
        this.checked = checked;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="question_id", unique=true, nullable=false)

    public Integer getQuestionId() {
        return this.questionId;
    }
    
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="user_id", nullable=false)

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    @Column(name="content", nullable=false, length=65535)

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    @Column(name="answer", nullable=false, length=65535)

    public String getAnswer() {
        return this.answer;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    @Column(name="checked", nullable=false)

    public Boolean getChecked() {
        return this.checked;
    }
    
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
   








}