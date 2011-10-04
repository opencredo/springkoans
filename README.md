Spring Koans
============

These Koans walk you along the path to enlightenment in order to learn Spring. The goal is to learn the Spring Framework's features gradually and engaging your brain as much as possible. At the heart of Spring's usage is effective support for Unit (by Spring getting out of the way) and integration (using Spring's Testing support) testing and so it was natural to provide these koans as a series of failing tests that you learn how to fix as you take a learning journey through the Spring Framework.

With a huge nod to the [EdgeCase Ruby Koans](http://rubykoans.com/) for the inspiration for this project.

Getting Started
---------------

To start your journey through the Spring Koans:

* Do a git clone to checkout the code, or download and unpack a tar.gz or .zip from [GitHub](http://github.com/opencredo/springkoans)
* Change directory into the springkoans/programmingspring directory
* Run "mvn clean test" from the command line (having installed [Maven](http://maven.apache.org/) first of course)
* You should then get an output like the following, which gives you some pointers on the first problem to contemplate and fix in the code:

[junit] Running com.programmingspring.koans.ApplicationContextKoan
[junit] Tests run: 1, Failures: 1, Errors: 0, Time elapsed: 0.006 sec
[junit] Testsuite: com.programmingspring.koans.ApplicationContextKoan
[junit] Tests run: 1, Failures: 1, Errors: 0, Time elapsed: 0.006 sec
[junit] 
[junit] Testcase: testKoan1CreateEmptyApplicationContext took 0.002 sec
[junit] 	FAILED
[junit] ApplicationContext is null. Please create an empty ApplicationContext.
[junit] junit.framework.AssertionFailedError: ApplicationContext is null. Please create an empty ApplicationContext.
[junit] 	at com.programmingspring.koans.ApplicationContextKoan.testKoan1CreateEmptyApplicationContext(ApplicationContextKoan.java:16)
[junit]

* Of course it also helps to have a copy of [Programming Spring from O'Reilly](http://shop.oreilly.com/product/0636920018056.do) as the koans were created to accompany this book, however if you don't have it (or we're still finishing it!) then the koans should still stand on their own and provide a great learning experience.
* You can use any editor you see fit, ideally one that supports Java and XML, to work with these koans, but we recommend using [SpringSource Tool Suite](http://www.springsource.com/developer/sts)
* Next steps are for you to fix that problem, contemplate on why the fix worked the way it did and then to re-run "mvn clean test" to move onto the next step on the journey.
* If you get stuck, check out the accompanying *-solution projects as they have the pointers on how to move to the next step if it's holding you up. However it's worth noting that the value in koans is the effort you put into figuring out the answers for yourself, so we recommend only using the solutions for checking your answer or if you really are dead stuck (which shouldn't happen, so please raise a [JIRA](http://dev.opencredo.com/jira/browse/OCSPRINGKOANS) if it does!)

Contributors
------------

The Spring Koans contributors to date include:

* Russ Miles (CEO of [OpenCredo](http://www.opencredo.com) and Author on [Programming Spring](http://shop.oreilly.com/product/0636920018056.do))
* Michal Bachman (Consultant at [OpenCredo](http://www.opencredo.com))

Contributing
------------

There are a number of ways you can get involved in this project:

* Create [JIRA](http://dev.opencredo.com/jira/browse/OCSPRINGKOANS) tickets for bugs and new features and comment and vote on existing ones you might be interested in.
* Github is for social coding: if you want to write code, we encourage contributions through pull requests from [forks of this repository](http://help.github.com/forking/). If you want to contribute code this way, please reference a JIRA ticket as well covering the specific issue you are addressing.
* Watch for upcoming articles and other materials related to the Spring Koans by [subscribing](http://www.opencredo.com/index.php?format=feed&type=rss) to OpenCredo.org

Before we accept a non-trivial patch or pull request we will need you to sign the contributor's agreement. Signing the contributor's agreement does not grant anyone commit rights to the main repository, but it does mean that we can accept your contributions, and you will get an author credit if we do. Active contributors might be asked to join the core team, and given the ability to merge pull requests.