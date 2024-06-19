package br.com.company.util;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transacional
@Priority(Interceptor.Priority.APPLICATION)
public class TransacionalInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction transaction = manager.getTransaction();
		boolean createTransacional = false;

		try {
			if (!transaction.isActive()) {
				transaction.begin();
				transaction.rollback();
				
				transaction.begin();
				createTransacional = true;
			}

			return context.proceed();
		} catch (Exception e) {
			if (transaction != null && createTransacional) {
				transaction.rollback();
			}

			throw e;
		} finally {
			if (transaction != null && transaction.isActive() && createTransacional) {
				transaction.commit();
			}
		}
	}

}