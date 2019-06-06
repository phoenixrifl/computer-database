package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import modele.Company;
import modele.QCompany;
import servlet.model.Pagination;

@Component
@Transactional(propagation = Propagation.NESTED)
public class CompanyDAO {

	@PersistenceContext
	EntityManager entityManager;

	protected final JPAQueryFactory jpaQueryFactory;
	private QCompany qCompany = QCompany.company;

	public CompanyDAO(JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
	}

	public Company find(long id) {
		return jpaQueryFactory.selectFrom(qCompany).where(qCompany.id_.eq(id)).fetchOne();

	}

	public List<Company> findAll(Pagination pagination) {
		return jpaQueryFactory.selectFrom(qCompany).limit(pagination.getLimit()).offset(pagination.getOffset()).fetch();

	}

	public List<Company> findAll() {
		return jpaQueryFactory.selectFrom(qCompany).fetch();

	}

	public void delete(long id) {
		jpaQueryFactory.delete(qCompany).where(qCompany.id_.eq(id)).execute();
	}

}