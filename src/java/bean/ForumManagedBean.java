package bean;

import controller.FawnUserSessionBean;
import controller.ForumPostingCommentSessionBean;
import controller.ForumPostingSessionBean;
import controller.ForumTopicSessionBean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import model.FawnUser;
import model.ForumPosting;
import model.ForumPostingComment;
import model.ForumTopic;

/**
 *
 * @author 1773031 Haryo Bagas Assyafah, 1873004 Alvira Puteri
 */
@Named(value = "forumManagedBean")
@SessionScoped
public class ForumManagedBean implements Serializable {

    @EJB
    private ForumPostingSessionBean postingMB;
    @EJB
    private ForumPostingCommentSessionBean commentMB;
    @EJB
    private ForumTopicSessionBean topicMB;

    private List<ForumTopic> modelTopic;
    private FawnUser selectedUser;
    private ForumTopic selectedTopicView;
    private ForumPosting editPost,
            selectedPost;
    private ForumPostingComment editPostComment;
    private boolean isAddPost, isViewUserProfile;

    public void initForum() {
        modelTopic = topicMB.getAllData();
        selectedTopicView = null;
        editPost = new ForumPosting();
        selectedPost = null;
        isAddPost = true;
        isViewUserProfile = false;
        editPostComment = null;
    }

    public String initForumUser(FawnUser fawnUser) {
        isViewUserProfile = true;
        selectedUser = fawnUser;
        return "forumUserProfile";
    }

    public String initForumPostDetails(ForumPosting forumPosting) {
        selectedPost = forumPosting;
        editPostComment = new ForumPostingComment();
        return "forumDetail";
    }

    public ForumPostingSessionBean getPostingMB() {
        return postingMB;
    }

    public void setPostingMB(ForumPostingSessionBean postingMB) {
        this.postingMB = postingMB;
    }

    public ForumPostingCommentSessionBean getCommentMB() {
        return commentMB;
    }

    public void setCommentMB(ForumPostingCommentSessionBean commentMB) {
        this.commentMB = commentMB;
    }

    public ForumTopicSessionBean getTopicMB() {
        return topicMB;
    }

    public void setTopicMB(ForumTopicSessionBean topicMB) {
        this.topicMB = topicMB;
    }

    public List<ForumTopic> getModelTopic() {
        return modelTopic;
    }

    public void setModelTopic(List<ForumTopic> modelTopic) {
        this.modelTopic = modelTopic;
    }

    public ForumTopic getSelectedTopicView() {
        return selectedTopicView;
    }

    public void setSelectedTopicView(ForumTopic selectedTopicView) {
        this.selectedTopicView = selectedTopicView;
    }

    public ForumPosting getEditPost() {
        return editPost;
    }

    public void setEditPost(ForumPosting editPost) {
        this.editPost = editPost;
    }

    public ForumPosting getSelectedPost() {
        return selectedPost;
    }

    public void setSelectedPost(ForumPosting selectedPost) {
        this.selectedPost = selectedPost;
    }

    public String initCreateNewPost() {
        editPost = new ForumPosting();
        editPost.setIDtopic(new ForumTopic());
        editPost.setContent("");
        editPost.setSubject("");
        isAddPost = true;
        return "forumManagePost";
    }

    public String initEditPost(ForumPosting fp) {
        editPost = new ForumPosting(fp);
        isAddPost = false;
        return "forumManagePost";
    }

    public String getManagePostType() {
        return isAddPost ? "Create New Post" : "Edit Post";
    }

    public void resetValue() {
        editPost.setIDtopic(null);
        editPost.setContent("");
        editPost.setSubject("");
    }

    public boolean isIsAddPost() {
        return isAddPost;
    }

    public void setIsAddPost(boolean isAddPost) {
        this.isAddPost = isAddPost;
    }

    public String addOrEditPost() {
        if (isAddPost) {
            editPost.setDate(new Date());
            editPost.setHasEdit(false);
            editPost.setEditDate(null);
            editPost.setIDuser(FawnUserSessionBean.loggedUser);
            postingMB.insert(editPost);
            return "forum";
        }
        editPost.setHasEdit(true);
        editPost.setEditDate(new Date());
        postingMB.update(editPost);
        return "forum";
    }

    public List<ForumPosting> getModelForumPostMain() {
        return postingMB.getAllDataFiltered(selectedTopicView);
    }

    public FawnUser getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(FawnUser selectedUser) {
        this.selectedUser = selectedUser;
    }

    public boolean isIsViewUserProfile() {
        return isViewUserProfile;
    }

    public void setIsViewUserProfile(boolean isViewUserProfile) {
        this.isViewUserProfile = isViewUserProfile;
    }

    public ForumPostingComment getEditPostComment() {
        return editPostComment;
    }

    public void setEditPostComment(ForumPostingComment editPostComment) {
        this.editPostComment = editPostComment;
    }

    public void addComment() {
        editPostComment.setHasEdit(false);
        editPostComment.setDate(new Date());
        editPostComment.setEditDate(null);
        editPostComment.setIDposting(selectedPost);
        editPostComment.setIDuser(FawnUserSessionBean.loggedUser);
        commentMB.insert(editPostComment);
        editPostComment = new ForumPostingComment();
        GlobaManagedBean.addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Comment posted");
    }

    public void deleteComment(ForumPostingComment fpc) {
        commentMB.delete(fpc);
        GlobaManagedBean.addMessage(FacesMessage.SEVERITY_INFO, "Notification", "Comment deleted");
    }

    public String deletePost() {
        postingMB.delete(selectedPost);
        return "forum";
    }

    public List<ForumPostingComment> modelForumNotification() {
        return commentMB.getAllCommentNotification(FawnUserSessionBean.loggedUser);
    }
}
