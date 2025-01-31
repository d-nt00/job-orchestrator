package personal.job.orchestrator.mapper;

public interface AbstractMapper<E, D> {
  E toEntity(D t);
  D toDto(E e);
}
