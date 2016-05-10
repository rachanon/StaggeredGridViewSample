# StaggeredGridViewSample

ListView and GridView just not complex enough? Try StaggeredGridView!

StaggeredGridView presents a multi-column grid with consistent column sizes but varying row sizes between the columns. Each successive item from a {@link ListAdapter ListAdapter} will be arranged from top to bottom, left to right. The largest vertical gap is always filled first.

Item views may span multiple columns as specified by their {@link LayoutParams}. The attribute android:layout_span may be used when inflating item views from xml.

There are a few things you should know if you're going to make modifications to StaggeredGridView.

Like ListView, SGV populates from an adapter and recycles views that fall out of the visible boundaries of the grid. A few invariants always hold:

- mFirstPosition is the adapter position of the View returned by getChildAt(0).
- Any child index can be translated to an adapter position by adding mFirstPosition.
- Any adapter position can be translated to a child index by subtracting mFirstPosition.
- Views for items in the range [mFirstPosition, mFirstPosition + getChildCount()) are currently attached to the grid as children. All other adapter positions do not have active views.

This means a few things thanks to the staggered grid's nature. Some views may stay attached long after they have scrolled offscreen if removing and recycling them would result in breaking one of the invariants above.

LayoutRecords are used to track data about a particular item's layout after the associated view has been removed. These let positioning and the choice of column for an item remain consistent even though the rules for filling content up vs. filling down vary.

Whenever layout parameters for a known LayoutRecord change, other LayoutRecords before or after it may need to be invalidated. e.g. if the item's height or the number of columns it spans changes, all bets for other items in the same direction are off since the cached information no longer applies.
