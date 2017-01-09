# babysitter kata solution

## the kata definition

From https://gist.github.com/jameskbride/5482722 thanks to @jameskbride:

> ### Babysitter Kata

> #### Background

> This kata simulates a babysitter working and getting paid for one night.  The rules are pretty straight forward:

> The babysitter 
> - starts no earlier than 5:00PM
> - leaves no later than 4:00AM
> - gets paid $12/hour from start-time to bedtime
> - gets paid $8/hour from bedtime to midnight
> - gets paid $16/hour from midnight to end of job
> - gets paid for full hours (no fractional hours)


> Feature:
> As a babysitter
> In order to get paid for 1 night of work
> I want to calculate my nightly charge

Wow, Jim, what parents stay out til 4 AM?

When is bedtime? I assume it's arbitrary. I'll also assume it's before midnight; otherwise, rules get confusing.

## things I need to test for

It's a good idea to sketch out the things we think we'll need tests around. I'll use this as my checklist and prioritize them as I go.

- [x] times should be able to convert themselves to minutes relative to a base time -- 5:00 PM sounds reasonable since it's the earliest start time
- [x] parse times in the formats:
    - [x] hh:mm AM
    - [x] hh:mm PM
    - [x] h:mm PM
- [x] time objects should measure elapsed time in full hours (rounded up)
- [x] time objects should return an elapsed time of zero for negative durations
- [x] time objects should know when they are on or before another time
- [x] time objects should know when they are on or after another time
- [x] simple payments
    - [x] before bedtime
    - [x] between bedtime and midnight
    - [x] after midnight
- [x] payments that span
    - [x] before bedtime + before midnight
    - [x] before midnight + after midnight
    - [ ] before bedtime + before midnight + after midnight 