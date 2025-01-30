package personal.job.orchestrator.repository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.relational.core.query.Update;
import org.springframework.stereotype.Component;
import personal.job.orchestrator.common.annotations.ValidateObject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@AllArgsConstructor
public abstract class BaseReactiveRepository<K, E, C extends Enum<C> & ColumnIdentity> {

  protected final R2dbcEntityTemplate r2dbcEntityTemplate;

  protected Class<E> entityClass;

  protected C primaryKey;

  private Query onColumn(C column, @ValidateObject Object expected) {
    return Query.query(Criteria.where(column.getName()).is(expected));
  }

  public Mono<E> findById(K id) {
    return r2dbcEntityTemplate.select(entityClass)
        .matching(onColumn(primaryKey, id))
        .one();
  }

  public Mono<Boolean> exists(K id) {
    return r2dbcEntityTemplate.select(entityClass)
        .matching(onColumn(primaryKey, id))
        .exists();
  }

  public Flux<E> findBy(C column, Object value) {
    return r2dbcEntityTemplate.select(entityClass)
        .matching(onColumn(column, value))
        .all();
  }

  public Mono<Long> updateOn(C column,
                             Object condition,
                             @ValidateObject Object newValue) {
    String columnName = column.getName();

    return r2dbcEntityTemplate.update(entityClass)
        .matching(onColumn(column, condition))
        .apply(Update.update(columnName, newValue));
  }
}
