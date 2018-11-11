package org.udg.caes.account;

import mockit.Expectations;
import mockit.Verifications;
import org.junit.jupiter.api.Test;

import mockit.Tested;
import mockit.Mocked;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAccountService {


  @Test
  void testTransfer(@Tested AccountService as, @Mocked AccountManager am)  {

    Account a = new Account("Usera",1000);
    Account b = new Account("Userb",5000);

    new Expectations(){{

      am.findAccount("Usera") ; result=a;

      am.findAccount("Userb"); result=b;


    }};

    as.transfer("Usera","Userb",1000);

    assertEquals(0,a.getBalance());
    assertEquals(6000,b.getBalance());

    new Verifications(){{

      am.updateAccount(a); times=1;
      am.updateAccount(b); times=1;
    }};
    
  }
}