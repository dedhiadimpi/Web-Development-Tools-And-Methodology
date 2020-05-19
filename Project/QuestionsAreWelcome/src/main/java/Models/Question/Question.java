/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Question;

import Models.Answer.Answer;
import Models.Approver.Approver;
import Models.Domain.Domain;
import Models.Likes.Likes;
import Models.User.User;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author dedhi
 */
@Entity
@Table(name = "tbl_questions")
public class Question{
    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long question_id;
    
    @Column(name = "question_title", length = 500)
    private String question_title;
    
    @Column(name = "question_description", length = 4000)
    private String question_description;
    
    @OneToOne(targetEntity = Domain.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "domain_id", referencedColumnName = "domain_id")
    private Domain domain;
    
    @OneToOne(targetEntity = Approver.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "approver_id", referencedColumnName = "approver_id")
    private Approver approver;
    
    @OneToOne(targetEntity = User.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "tbl_question_answer", joinColumns = { @JoinColumn(name = "question_id") }, inverseJoinColumns = { @JoinColumn(name = "answer_id") })
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL)
//    @Cascade(value = { CascadeType.REMOVE, CascadeType.SAVE_UPDATE })
    private Set<Answer> answerList;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Likes> likes = new HashSet<Likes>(0);
    
    
    @Column(name = "reject_comment", length = 4000)
    private String reject_comment;
    
    @Column(name = "no_of_likes")
    private int no_of_likes;
    
    @Column(name = "no_of_dislikes")
    private int no_of_dislikes;
    
    @Column(name = "created_on")
    private Date created_on;
    
    @Column(name = "updated_on")
    private Date updated_on;

    public Set<Likes> getLikes() {
        return likes;
    }

    public void setLikes(Set<Likes> likes) {
        this.likes = likes;
    }

    public Question() {
        this.answerList = new HashSet<Answer>(0);
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public Date getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(Date updated_on) {
        this.updated_on = updated_on;
    }
    
    public int getNo_of_likes() {
        return no_of_likes;
    }

    public void setNo_of_likes(int no_of_likes) {
        this.no_of_likes = no_of_likes;
    }

    public int getNo_of_dislikes() {
        return no_of_dislikes;
    }

    public void setNo_of_dislikes(int no_of_dislikes) {
        this.no_of_dislikes = no_of_dislikes;
    }
            
            

    public String getReject_comment() {
        return reject_comment;
    }

    public void setReject_comment(String reject_comment) {
        this.reject_comment = reject_comment;
    }
    
    

    public Set<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(Set<Answer> answerList) {
        this.answerList = answerList;
    }


    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public Approver getApprover() {
        return approver;
    }

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_title() {
        return question_title;
    }

    public void setQuestion_title(String question_title) {
        this.question_title = question_title;
    }

    public String getQuestion_description() {
        return question_description;
    }

    public void setQuestion_description(String question_description) {
        this.question_description = question_description;
    }
    
}
