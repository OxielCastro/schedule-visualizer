Feature: Apple Map

Scenario: Different people can start with different number of apples
Given John starts with 5 apples
  And Jane starts with 3 apples
 Then John will have 5 apples
  And Jane will have 3 apples

Scenario: One person can pass apples to another
Given John starts with 3 apples
  And Peter starts with 2 apples
 When John gives 2 apples to Peter
 Then John will have 1 apple
  And Peter will have 4 apples
