package com.capg.paymentwallet.dao;

import com.capg.paymentwallet.bean.AccountBean;



public interface IAccountDao {


    public boolean createAccount(AccountBean accountBean) throws Exception ;
    public boolean updateAccount(AccountBean accountBean) throws Exception;
    public AccountBean findAccount(int accountId) throws Exception;
	public boolean deposit(AccountBean accountBean,double depositAmount) throws Exception;
	public boolean withDraw(AccountBean accountBean,double withDrawAmount) throws Exception;
	 public boolean fundTransfer(AccountBean transferingAccountBean, AccountBean beneficiaryAccountBean, double transferAmount) throws Exception ;
     
  
  
	
	 
    
}
