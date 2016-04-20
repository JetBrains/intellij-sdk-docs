---
title: Build Number Ranges
---

Use this reference of build number ranges to specify the correct `since-build` and `until-build` values in your plugin descriptor.

Starting with IntelliJ IDEA 9 beta, a multi-part build number is used, such as:

```
IU-90.94
```

The number consists of the following parts:

* Product ID (IC for IDEA Community, IU for IDEA Ultimate, RM for RubyMine, PY for PyCharm, etc.)
* Branch number ("90")
* Build number in the branch ("94")

Since the version 2016.2 of IntelliJ Platform, branch and  build numbers mimic the [IDE version scheme](https://blog.jetbrains.com/blog/2016/03/09/jetbrains-toolbox-release-and-versioning-changes/): they are the shortened Platform versions: `162` for 2016.2, `163` for 2016.3, `171` for 2017.1. In this scheme `IU-163.94` corresponds to the 2016.3 releases.

Starting with 2016.2 the build number may also have multiple components:
```
IU-162.94
IU-162.94.11
IU-162.94.11.256.42
```
This gives more flexibility for third-party plugins and IDE developers. Plugins may specify compatibility versions more precisely; IDE vendors may have build numbers that are based on a specific IntelliJ Platform version and also specify additional internal version (e.g. 256.42 in XX-162.94.11.256.42) to allow plugin developers for their IDE to specify compatibility.



Multi-part build numbers can also be used in the `since-build` and `until-build` attributes of `idea-version`. Usually you should omit the product ID and use only the branch number and build number, for example:

```xml
<idea-version since-build="94.539"/>
<idea-version since-build="162.539.11"/>
<idea-version until-build="162"/> <!-- any build intil 162, not inclusive!-->
<idea-version since-build="162" until-build="162.*"/> <!-- any 162-based version, 162.94, 162.94.11, etc.-->
```

The following branch numbers are used for IntelliJ Platform-based products and build numbers of recent IDE versions:

* ...
* branch 171 - IntelliJ Platform 2017.1
* branch 163 - IntelliJ Platform 2016.3
* branch 162 - IntelliJ Platform 2016.2

* branch 145 - IntelliJ IDEA 2016.1, WebStorm 2016.1, PyCharm 2016.1, PhpStorm 2016.1, RubyMine 2016.1, AppCode 2016.1, CLion 2016.1, DataGrip 2016.1
* branch 143 - IntelliJ IDEA 15, WebStorm 11, PyCharm 5, PhpStorm 10, RubyMine 8, AppCode 3.3, CLion 1.2, DataGrip 1.0
* branch 141 - IntelliJ IDEA 14.1, WebStorm 10, PyCharm 4.1, Android Studio 1.3
* branch 139 - IntelliJ IDEA 14, WebStorm 9, PyCharm 4, PhpStorm 8, RubyMine 7
* branch 135 - IntelliJ IDEA 13.1, WebStorm 8
* branch 133 - IntelliJ IDEA 13, PyCharm 3.1, WebStorm 7, PhpStorm 7
* branch 131 - WebStorm 7, PyCharm 3.0, PhpStorm 7
* branch 129 - IntelliJ IDEA 12.1, bug-fix updates for PyCharm 2.7, PhpStorm/WebStorm 6
* branch 127 - PhpStorm/WebStorm 6.0, AppCode 2.0
* branch 125 - PyCharm 2.7, RubyMine 5.0
* branch 123 - IntelliJ IDEA 12
	* 12.0 - 123.72
* branch 121 - AppCode 1.6, PyCharm 2.6, PhpStorm/WebStorm 5.0
* branch 119 - RubyMine 4.5
* branch 117 - IntelliJ IDEA 11.1, PyCharm 2.5, RubyMine 4.0.x, AppCode 1.5, PhpStorm/WebStorm 4.0
	* 11.1 - 117.105
	* 11.1.1 - 117.117
	* 11.1.2 - 117.418
	* 11.1.3 - 117.798
* branch 111 - IntelliJ IDEA 11.0
	* 11.0 - 111.69
	* 11.0.1 - 111.167
	* 11.0.2 - 111.277
* branch 107 - IDEA 10.5
	* 10.5 - 107.105
	* 10.5.1 - 107.322
	* 10.5.2 - 107.587
* branch 103 - IDEA 10.0.2\+
	* 10.0.2 - 103.72
	* 10.0.3 - 103.255
* 10.0 - 99.18
    * 10.0.1 - 99.32
* branch 95 - IDEA 9.0.2\+
    * 9.0.2 - 95.66
    * 9.0.3 - 95.429
    * 9.0.4 - 95.627
    * branch 99 - IDEA 10.0
* branch 93 - IDEA 9.0
    * 9.0 - 93.13
    * 9.0.1 - 93.94

---

Previous versions of IntelliJ IDEA use linear build numbers, with the following ranges:

* IntelliJ IDEA 8.1.x - 9500-9999
	* IntelliJ IDEA 8.1 - 9732
* IntelliJ IDEA 8.0.x - 9100-9499
	* IntelliJ IDEA 8.0.1 - 9164
* IntelliJ IDEA 8.0 - 8000-9099
	* IntelliJ IDEA 8.0M1 - 8664
	* IntelliJ IDEA 8.0 - 9013
* IntelliJ IDEA 7.0.2\+ - 7500-7999
	* 7.0.2 - 7590
	* 7.0.3 - 7757
	* 7.0.5 - 7971
* IntelliJ IDEA 7.0 final - 7200-7499
	* 7.0 final - 7361
* IntelliJ IDEA 7.0 pre-M2 - 6900-7199
	* 7.0 M2 - 7126
* IntelliJ IDEA 7.0 pre-M1 - 6500-6899
	* 7.0 M1 - 6813
* IntelliJ IDEA 6.0.2 branch - 6000-6499
	* 6.0.5 - 6180
	* 6.0.6 - 6197
* IntelliJ IDEA 6.0 branch - 5000-5999
	* 6.0.1 - 5784
* IntelliJ IDEA 5.1 branch - 4000-4999
	* 5.1.2 - 4267
