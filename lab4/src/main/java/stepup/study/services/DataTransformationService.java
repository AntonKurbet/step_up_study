package stepup.study.services;

import java.util.List;

public interface DataTransformationService<T> {
    List<T> transform(List<T> list, String... args);
}
