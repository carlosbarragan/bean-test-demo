/**
 * 
 */
package info.novatec.beantest.demo.mocks;

import info.novatec.beantest.demo.backend.BackEndOrderSystem;

import javax.enterprise.inject.Produces;

import org.mockito.Mockito;

/**
 * @author cb
 * 
 */
public class MockProducer {

    @Produces
    public static final BackEndOrderSystem backEndOrderSystemMock = Mockito
            .mock(BackEndOrderSystem.class);
}
