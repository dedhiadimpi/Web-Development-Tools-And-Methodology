/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Answer;

import Models.Approver.Approver;
import Models.Domain.Domain;
import Models.Likes.Likes;
import Models.Question.Question;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author dedhi
 */
@Entity
@Table(name = "tbl_answers")
public class Answer{
    @Id
    @Column(name = "answer_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long answer_id;
    @Column(name = "answer_description", length = 4000)
    private String answer_description;
    @OneToOne(targetEntity = Approver.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "approver_id", referencedColumnName = "approver_id")
    private Approver approver;
    @OneToOne(targetEntity = User.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "answer",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Likes> likes = new HashSet<Likes>(0);

    public Set<Likes> getLikes() {
        return likes;
    }

    public void setLikes(Set<Likes> likes) {
        this.likes = likes;
    }
    
    @Column(name = "no_of_likes")
    private int no_of_likes;
    
    @Column(name = "no_of_dislikes")
    private int no_of_dislikes;
    
    @Column(name = "created_on")
    private Date created_on;
    
    @Column(name = "updated_on")
    private Date updated_on;

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
    
    @Column(name="reject_comment", length = 4000)
    private String reject_comment;

    public String getReject_comment() {
        return reject_comment;
    }

    public void setReject_comment(String reject_comment) {
        this.reject_comment = reject_comment;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public long getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(long answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswer_description() {
        return answer_description;
    }

    public void setAnswer_description(String answer_description) {
        this.answer_description = answer_description;
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
    
}
