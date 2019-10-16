---
title: Plugins Rating
---

The rating which is shown for each plugin in the plugin repository is a Bayesian rating, the exact formula of which is:
```
bayesianRating = (sum(userRatings) + 2 * meanPluginRating) / (count(userRatings) + 2)
```
It may confuse that the plugin rating is not 5 while all votes are 5. However, the reason for using the Bayesian rating is avoiding the situation when the plugins with few numbers of 5 votes are above the plugins having many votes but not all of which are 5.