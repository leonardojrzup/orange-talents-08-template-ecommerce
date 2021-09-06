package br.com.leonardo.mercadolivre.annotations;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistisValidator implements ConstraintValidator<Exists, Object> {

    private String campo;
    private Class<?> classe;

    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void initialize(Exists params) {
        campo = params.fieldName();
        classe = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null)
            return true;

        Query query = entityManager.createQuery("select 1 from " + classe.getName() + " where " + campo + " = :value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        return !list.isEmpty();
    }
}
