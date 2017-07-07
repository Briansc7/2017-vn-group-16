package model.metodologia.condiciones;

import java.lang.reflect.Type;

import com.google.gson.InstanceCreator;

public class BooleanConditionInstanceCreator<T> implements InstanceCreator<BooleanCondition> {

	@Override
	public BooleanCondition createInstance(Type tipo) {
		return new EqualThan();
	}

}
