# knighttour

This repository contains the code for solving a knight's tour in a concurrent manner.

The problem with using multiple cores is access to states needs to be synchronized which is tricky. Divide and condquer approach can be explored to minimize the synchronous access to shared data.
