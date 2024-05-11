package org.moflon.emf.codegen;

import org.eclipse.emf.common.notify.Adapter;
import org.moflon.emf.injection.ide.InjectionManager;

public class InjectionAwareGeneratorAdapterFactory extends GeneratorAdapterFactory
implements org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor {
	public InjectionAwareGeneratorAdapterFactory(final InjectionManager injectionManager) {
		this.injectionManager = injectionManager;
	}

	@Override
	public Adapter createGenClassAdapter() {
		if (this.genClassGeneratorAdapter == null) {
			this.genClassGeneratorAdapter = new InjectionAwareClassGeneratorAdapter(this);
		}
		return this.genClassGeneratorAdapter;
	}

	@Override
	public InjectionAwareGeneratorAdapterFactory createAdapterFactory() {
		return this;
	}
}