package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.querydsl.jpa.impl.JPAQueryFactory;

import modele.Computer;
import modele.QCompany;
import modele.QComputer;
import servlet.model.Pagination;

@Component
public class ComputerDAO {

	private static final String SQL_UPDATE = "UPDATE computer SET name=:name, introduced=:introduced, discontinued=:discontinued, company_id=:company_id WHERE id =:id";
	private static final String SQL_PAGE = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
			+ "FROM computer LEFT JOIN company ON computer.company_id = company.id ORDER BY ";
	private static final String SQL_SELECT = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id ";
	private static final String SQL_COUNT = "SELECT COUNT(*) AS total FROM computer";
	private static final String SQL_SEARCH = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company_id, company.name "
			+ "FROM computer LEFT JOIN company ON company_id = company.id "
			+ "WHERE computer.name LIKE :computerName OR company.name LIKE :companyName ORDER BY ";
	private static final String SQL_COUNT_SEARCH = "SELECT COUNT(*) AS total FROM computer LEFT JOIN company ON computer.company_id = company.id "
			+ "WHERE UPPER(computer.name) LIKE UPPER(:computerName) OR  UPPER(company.name) LIKE UPPER(:companyName) ";

	private static final String SQL_LIMIT_OFFSET = " LIMIT :limit OFFSET :offset";

	@PersistenceContext
	EntityManager entityManager;

	protected final JPAQueryFactory jpaQueryFactory;
	private QComputer qComputer = QComputer.computer;
	private QCompany qCompany = QCompany.company;

	public ComputerDAO(JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
	}

	public void create(Computer obj) {
		entityManager.persist(obj);
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
		return jpaQueryFactory.selectFrom(qComputer).leftJoin(qCompany).on(qCompany.id_.eq(qComputer.id_))
				.where(qComputer.id_.eq(id)).fetchOne();

	}

	public long count() throws DataAccessException {
		return jpaQueryFactory.selectFrom(qComputer).fetchCount();
	}

	public long countSearch(Pagination pagination) {
		return jpaQueryFactory.selectFrom(qComputer).leftJoin(qCompany).on(qCompany.id_.eq(qComputer.id_))
				.where(qComputer.name.like("%" + pagination.getSearch() + "%")
						.or(qCompany.name.like("%" + pagination.getSearch() + "%")))
				.fetchCount();
	}

	public List<Computer> findAll() {
		return jpaQueryFactory.selectFrom(qComputer).leftJoin(qCompany).on(qCompany.id_.eq(qComputer.id_)).fetch();
	}

	public List<Computer> findAll(Pagination pagination) {
		return jpaQueryFactory.selectFrom(qComputer).leftJoin(qCompany).on(qCompany.id_.eq(qComputer.id_))
				.limit(pagination.getLimit()).offset(pagination.getOffset()).fetch();
	}

	public List<Computer> searchComputers(Pagination pagination) {
		return jpaQueryFactory.selectFrom(qComputer).leftJoin(qCompany).on(qCompany.id_.eq(qComputer.id_))
				.where(qComputer.name.like("%" + pagination.getSearch() + "%")
						.or(qCompany.name.like("%" + pagination.getSearch() + "%")))
				.limit(pagination.getLimit()).offset(pagination.getOffset()).fetch();
	}

}
