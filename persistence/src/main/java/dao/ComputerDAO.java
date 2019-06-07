package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import model.Computer;
import model.QCompany;
import model.QComputer;
import servlet.model.Pagination;

@Component
@Transactional(propagation = Propagation.NESTED)
public class ComputerDAO {

	@PersistenceContext
	EntityManager entityManager;

	protected final JPAQueryFactory jpaQueryFactory;
	private QComputer qComputer = QComputer.computer;
	private QCompany qCompany = QCompany.company;

	public ComputerDAO(JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
	}

	public void create(Computer obj) {
		this.entityManager.merge(obj);
	}

	public void delete(Computer obj) {
		jpaQueryFactory.delete(qComputer).where(qComputer.id_.eq(obj.getId_())).execute();
	}

	public void update(Computer obj) {
		jpaQueryFactory.update(qComputer).where(qComputer.id_.eq(obj.getId_())).set(qComputer.id_, obj.getId_())
				.set(qComputer.name, obj.getName()).set(qComputer.introduced, obj.getIntroduced())
				.set(qComputer.discontinued, obj.getDiscontinued()).set(qComputer.company, obj.getCompany()).execute();

	}

	public Computer find(long id) throws DataAccessException {
		return jpaQueryFactory.selectFrom(qComputer).leftJoin(qCompany).on(qCompany.id_.eq(qComputer.company.id_))
				.where(qComputer.id_.eq(id)).fetchOne();

	}

	public long count() throws DataAccessException {
		return jpaQueryFactory.selectFrom(qComputer).fetchCount();

	}

	public long countSearch(Pagination pagination) {
		return jpaQueryFactory.selectFrom(qComputer).leftJoin(qCompany).on(qCompany.id_.eq(qComputer.company.id_))
				.where(qComputer.name.like("%" + pagination.getSearch() + "%")
						.or(qCompany.name.like("%" + pagination.getSearch() + "%")))
				.orderBy(pagination.getOrderby().getField()).fetchCount();

	}

	public List<Computer> findAll() {
		return jpaQueryFactory.selectFrom(qComputer).leftJoin(qCompany).on(qCompany.id_.eq(qComputer.company.id_))
				.fetch();

	}

	public List<Computer> findAll(Pagination pagination) {
		return jpaQueryFactory.selectFrom(qComputer).leftJoin(qCompany).on(qCompany.id_.eq(qComputer.company.id_))
				.orderBy(pagination.getOrderby().getField()).limit(pagination.getLimit()).offset(pagination.getOffset())
				.fetch();
	}

	public List<Computer> searchComputers(Pagination pagination) {
		return jpaQueryFactory.selectFrom(qComputer).leftJoin(qCompany).on(qCompany.id_.eq(qComputer.company.id_))
				.where(qComputer.name.like("%" + pagination.getSearch() + "%")
						.or(qCompany.name.like("%" + pagination.getSearch() + "%")))
				.orderBy(pagination.getOrderby().getField()).limit(pagination.getLimit()).offset(pagination.getOffset())
				.fetch();
	}

}
