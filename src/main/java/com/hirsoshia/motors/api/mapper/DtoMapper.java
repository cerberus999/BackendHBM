package com.hirsoshia.motors.api.mapper;

public interface DtoMapper<E, D> {
    D toDto(E entity);
}
