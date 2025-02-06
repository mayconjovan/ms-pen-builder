package com.mjp.factory.domain.repositories;


import com.mjp.factory.domain.model.Pen;

import java.util.List;

public interface PenRepository {

    void saveAll(List<Pen> pens);


}
