# bean-test-demo
Small project to demonstrate [BeanTest](https://github.com/NovaTecConsulting/BeanTest).

For every unit test there is a corresponding bean test.
The branch *demo* contains an extra test method in the class *OrderServiceBeanTest*. The method tests the *OrderService.placeOrder()*. 
In order to successfully test that method, the *OrderService* class had to be modified.

If you apply a *diff* to see the differences between the master and demo branch you will see what has exactly changed. 
This changes demonstrate the type of errors you can find with beanTest but not with normal unit-Tests

## Requirements
* Maven
* Java 8

