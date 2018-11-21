package com.capg.paymentwallet.dao;

import javax.persistence.EntityManager;

import com.capg.paymentwallet.bean.AccountBean;

public class AccountDAOImpl implements IAccountDao {

	
	EntityManager em;
	
	@Override
	public boolean createAccount(AccountBean accountBean) throws Exception {
		try{
			
			this.em=JPAManager.createEntityManager();
			em.getTransaction().begin();
			
			em.persist(accountBean);
			
			em.getTransaction().commit();
			JPAManager.closeResources(em);
			
			return true;
		}catch(Exception e){

			return false;
		}
	
	}

	@Override
	public boolean updateAccount(AccountBean accountBean) throws Exception {
		try{
			this.em=JPAManager.createEntityManager();
			em.getTransaction().begin();
			
			em.merge(accountBean);
			
			em.getTransaction().commit();
			JPAManager.closeResources(em);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	
	}
	public boolean deposit(AccountBean accountBean, double depositAmount) throws Exception {
		
		accountBean.setBalance(accountBean.getBalance()+depositAmount);
		
		
		return updateAccount(accountBean);
		
	}
	
	public boolean withDraw(AccountBean accountBean, double withdrawAmount)
			throws Exception {
		double balance=accountBean.getBalance()-withdrawAmount;
		accountBean.setBalance(balance);
		
		return updateAccount(accountBean);
	}

	@Override
	public AccountBean findAccount(int accountId) throws Exception {
		try{
			em=JPAManager.createEntityManager();
			AccountBean accountBean2=em.find(AccountBean.class,accountId);
			JPAManager.closeResources(em);
			return accountBean2;
			
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public boolean fundTransfer(AccountBean transferingAccountBean,
			AccountBean beneficiaryAccountBean, double transferAmount)
			throws Exception {
		transferingAccountBean.setBalance(transferingAccountBean.getBalance()-transferAmount);
		beneficiaryAccountBean.setBalance(beneficiaryAccountBean.getBalance()+transferAmount);
		
		boolean result1=updateAccount(transferingAccountBean);
		boolean result2=updateAccount(beneficiaryAccountBean);
		return result1 && result2;
	}

	

}
