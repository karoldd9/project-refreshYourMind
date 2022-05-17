package com.hibernate.classes.relations;

import com.hibernate.classes.relations.manyToMany.Article;
import com.hibernate.classes.relations.manyToMany.Tag;
import com.hibernate.classes.relations.oneToOne.Parcel;
import com.hibernate.classes.relations.oneToOne.ParcelAddress;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.HashSet;
import java.util.Set;

public class RelationRunner {

    private static Set<Tag> mergeTags(Tag... tags) {
        Set<Tag> tagSet = new HashSet<>();
        for(Tag tag: tags) {
            tagSet.add(tag);
        }

        return tagSet;
    }

    private static Set<Article> mergeArticles(Article... articles) {
        Set<Article> articleSet = new HashSet<>();
        for(Article article: articles) {
            articleSet.add(article);
        }

        return articleSet;
    }

    public static void main(String[] args) {
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        /*
        Question question = new Question("What is the sum of 3 and 4?");

        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("8", false));
        answers.add(new Answer("7", true));
        answers.add(new Answer("101", false));

        question.setAnswers(answers);

        session.persist(question);
        transaction.commit();
         - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        ParcelAddress parcelAddress = new ParcelAddress();
        parcelAddress.setAddress("Laponia, komin 17");
        Parcel parcel = new Parcel();
        parcel.setParcelAddress(parcelAddress);
        parcel.setPrice(100);
        parcel.setWarehouse("Warehouse in the Warsaw");

        session.persist(parcel);
        transaction.commit();

         */

        Article a1 = new Article();
        a1.setTitle("Spider-Man");
        a1.setContent("Spider-Man visited us again");

        Article a2 = new Article();
        a2.setTitle("Super-Man");
        a2.setContent("Super-Man visited us again");

        Article a3 = new Article();
        a3.setTitle("Spider-Man vs Super-Man");
        a3.setContent("Super-Man has been defeated");


        Tag t1 = new Tag();
        t1.setName("Spider-Man");

        Tag t2 = new Tag();
        t2.setName("Super-Man");

        Tag t3 = new Tag();
        t3.setName("Super-Hero");

        a1.setTags(mergeTags(t1, t3));
        a2.setTags(mergeTags(t2, t3));
        a3.setTags(mergeTags(t1, t2, t3));

        t1.setArticles(mergeArticles(a1, a3));
        t2.setArticles(mergeArticles(a2, a3));
        t3.setArticles(mergeArticles(a1, a2, a3));

        session.persist(a1);
        session.persist(a2);
        session.persist(a3);

        session.persist(t1);
        session.persist(t2);
        session.persist(t3);

        transaction.commit();

        sessionFactory.close();
        session.close();
    }
}
