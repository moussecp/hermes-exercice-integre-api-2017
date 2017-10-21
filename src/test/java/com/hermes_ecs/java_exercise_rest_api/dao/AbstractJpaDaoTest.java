package com.hermes_ecs.java_exercise_rest_api.dao;

import com.google.common.base.Optional;
import com.hermes_ecs.java_exercise_rest_api.domain.Identifiable;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceConfigurationTest.class)
@Transactional
public abstract class AbstractJpaDaoTest<I, T extends Identifiable<I>> {
    @PersistenceContext(name = "wattoApiDataStoreTest")
    private EntityManager em;
    private Dao<I, T> dao;

    @Before
    public void setUp() {
        dao = getDao();
    }

    @Test
    public void findPersistedItem() {
        // given
        T originalItem = buildItem();

        // when
        dao.persist(originalItem);
        em.flush();
        em.clear();

        // then
        assertThat(originalItem.getId(), is(notNullValue()));

        // when
        final Optional<T> persistedItem = dao.find(originalItem.getId());

        // then
        assertThat(persistedItem.isPresent(), is(true));
        assertThat(persistedItem.get(), is(equalTo(originalItem)));
    }

    @Test
    public void exists() {
        // given
        T originalItem = buildItem();

        // when
        dao.persist(originalItem);
        em.flush();
        em.clear();

        // then
        assertThat(dao.exists(originalItem.getId()), is(true));
    }

    @Test
    public void findAll() {
        // given
        T originalItem1 = buildItem(1);
        T originalItem2 = buildItem(2);

        // when
        dao.persist(originalItem1);
        dao.persist(originalItem2);
        em.flush();
        em.clear();

        // then
        assertThat(dao.findAll(), Matchers.containsInAnyOrder(originalItem1, originalItem2));
    }

    protected T buildItem() {
        return buildItem(0);
    }

    protected abstract T buildItem(int i);

    protected abstract Dao<I, T> getDao();
}
