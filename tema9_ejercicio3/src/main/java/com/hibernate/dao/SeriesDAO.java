package com.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


import com.hibernate.model.Series;
import com.hibernate.util.HibernateUtil;

public class SeriesDAO {
	public   void insertSeries(Series s) {
		Transaction transaction=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()) {
		transaction=session.beginTransaction();
		session.persist(s);
		transaction.commit();
		} catch (Exception e) {
		if (transaction!=null) {
		transaction.rollback();
		}
		}
		}
	public void updateSeries(Series s) {
		Transaction transaction=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()) {
		transaction=session.beginTransaction();
		session.merge(s);
		transaction.commit();
		} catch (Exception e) {
		if (transaction!=null) {
		transaction.rollback();
		}
		}
		}
	public void deleteSeries(int id) {
		Transaction transaction=null;
		Series s=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()) {
		transaction=session.beginTransaction();
		s=session.get(Series.class, id);
		session.remove(s);
		transaction.commit();
		} catch (Exception e) {
		if (transaction!=null) {
		transaction.rollback();
		}
		}
		}
	
	public Series selectSeriesById(int id) {
		Transaction transaction=null;
		Series s=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()) {
		transaction=session.beginTransaction();
		s=session.get(Series.class, id);
		transaction.commit();
		} catch (Exception e) {
		if (transaction!=null) {
		transaction.rollback();
		}
		}
		return s;
		}
	public List<Series> selectAllSeries() {
		Transaction transaction=null;
		List<Series> series=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()) {
		transaction=session.beginTransaction();
		series=session.createQuery("FROM Series",Series.class).getResultList();
		transaction.commit();
		} catch (Exception e) {
		if (transaction!=null) {
		transaction.rollback();
		}
		}
		return series;
		}
}

