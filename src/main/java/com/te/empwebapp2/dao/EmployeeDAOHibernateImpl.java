package com.te.empwebapp2.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.te.empwebapp2.beans.EmployeeInfoBean;
import com.te.empwebapp2.customexp.EmployeeException;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	@Override
	public EmployeeInfoBean authenticate(int id, String pwd) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		EmployeeInfoBean infoBean = manager.find(EmployeeInfoBean.class, id);

		if (infoBean != null) {
			if (infoBean.getPassword().equals(pwd)) {
				return infoBean;
			} else {
				throw new EmployeeException("Password is wrong");
			}
		} else {
			throw new EmployeeException("Invalid ID");
		}

	}

	@Override
	public EmployeeInfoBean getEmployeeData(int id) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		EmployeeInfoBean infoBean = manager.find(EmployeeInfoBean.class, id);
		manager.close();
		factory.close();
		return infoBean;
	}

	@Override
	public boolean deleteEmpData(int id) {
		boolean isDeleted = false;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			EmployeeInfoBean infoBean = manager.find(EmployeeInfoBean.class, id);
			manager.remove(infoBean);
			transaction.commit();
			isDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

		return isDeleted;
	}

	@Override
	public boolean addEmployee(EmployeeInfoBean employeeInfoBean) {
		boolean isInserted = false;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		try {
			transaction.begin();
			manager.persist(employeeInfoBean);
			transaction.commit();
			isInserted = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

		return isInserted;
	}

	@Override
	public boolean updateRecord(EmployeeInfoBean employeeInfoBean) {
		boolean isUpdated = false;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			EmployeeInfoBean actualInfo = manager.find(EmployeeInfoBean.class, employeeInfoBean.getId());

			if (employeeInfoBean.getName() != null && employeeInfoBean.getName() != "") {
				actualInfo.setName(employeeInfoBean.getName());
			}

			if (employeeInfoBean.getDob() != null) {
				actualInfo.setDob(employeeInfoBean.getDob());
			}

			if (employeeInfoBean.getPassword() != null && employeeInfoBean.getPassword() != "") {
				actualInfo.setPassword(employeeInfoBean.getPassword());
			}

			transaction.commit();
			isUpdated = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

		return isUpdated;
	}

	@Override
	public List<EmployeeInfoBean> getAllEmployees() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("from EmployeeInfoBean");
		ArrayList<EmployeeInfoBean> employeeInfoBeans = new ArrayList<EmployeeInfoBean>();
		try {
			employeeInfoBeans = (ArrayList<EmployeeInfoBean>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			employeeInfoBeans = null;
		}

		return employeeInfoBeans;
	}

}