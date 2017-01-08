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

- [ ] time objects should measure elapsed time in full hours (rounded up)
- [ ] parse times in the formats:
    - [ ] hh:mm AM
    - [ ] hh:mm PM
    - [ ] h:mm PM