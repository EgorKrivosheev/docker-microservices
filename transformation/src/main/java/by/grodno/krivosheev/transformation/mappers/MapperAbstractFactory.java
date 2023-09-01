package by.grodno.krivosheev.transformation.mappers;

import by.grodno.krivosheev.transformation.dto.AbstractDTO;
import by.grodno.krivosheev.transformation.entities.AbstractEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;

import java.util.List;

@MapperConfig(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MapperAbstractFactory<D extends AbstractDTO, E extends AbstractEntity> {

    D entityToDTO(E entity);

    List<D> listEntityToListDTO(List<E> listEntity);
}
