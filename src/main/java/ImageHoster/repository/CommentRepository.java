package ImageHoster.repository;

import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;


@Repository
public class CommentRepository {


    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;


    public Comment createcomment(Comment comment) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(comment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return comment;
    }

    public List<Comment> getAllComments(Integer imageId , String imageTitle) {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Comment> query = em.createQuery("SELECT c from Comment c where c.image.id=:imageid", Comment.class).setParameter("imageid",imageId);
            List<Comment> resultList = query.getResultList();
            return resultList;
        }
        catch (NoResultException nre){
            return null ;
        }
    }

}
