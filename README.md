Media Fragments URI 1.0 for Java
=================================

This is a Java implementation of W3C's Media Fragments URI 1.0 specification (http://www.w3.org/TR/media-frags/).
It is based on W3C's Parser http://www.w3.org/2008/WebVideo/Fragments/code/grammar/FragmentGrammar.jj

Example
=======

The library can be used to parse and serialize media fragments. Additionally it implements several spacio-temporal
functions (e.g. above, below, before, after, etc). Here are some examples, for more information look at the
tests [here](src/test/java/com/github/tkurz/media/fragments).

Parsing Media Fragment
----------------------

```java

    String s = "http://example.org/video.mp4#t=10,20&xywh=160,120,320,240";

    MediaFragmentURI uri = new MediaFragmentURI(s);

    if(uri.getMediaFragment().hasTemporalFragment() &&
            uri.getMediaFragment().getTemporalFragment() instanceof NPTFragment
    ) {

        NPTFragment npt = (NPTFragment) uri.getMediaFragment().getTemporalFragment();

        double start = npt.getStart().getValue();

    }

    if(uri.getMediaFragment().hasSpatialFragment()) {

        double w = uri.getMediaFragment().getSpatialFragment().getW();

        SpatialFragment.Unit unit = uri.getMediaFragment().getSpatialFragment().getUnit();

    }

```

Serializing Media Fragments
---------------------------

```java

    MediaFragmentURI uri = new MediaFragmentURI("http://example.org/video.mp4");

    uri.getMediaFragment().setSpatialFragment(new SpatialFragment(10,20,30,40));

    uri.getMediaFragment().setTemporalFragment(new NPTFragment(Clocktime.ZERO,new Clocktime(10)));

    System.out.println(uri); // prints 'http://example.org/video.mp4#xywh=10,20,30,40&t=,10'

```

Temporal Functions
------------------

```java

    MediaFragmentURI uri1 = new MediaFragmentURI("http://example.org/video.mp4#t=10.1,10");
    MediaFragmentURI uri2 = new MediaFragmentURI("http://example.org/video.mp4#t=20,30");

    TemporalFragment t1 = uri1.getMediaFragment().getTemporalFragment();
    TemporalFragment t2 = uri2.getMediaFragment().getTemporalFragment();

    System.out.println(t1.overlaps(t2)); //prints 'false'

    System.out.println(t1.getBoundingBox(t2)); //prints 't=10.1,30'

```

Spatial Functions
------------------

```java

    MediaFragmentURI uri1 = new MediaFragmentURI("http://example.org/video.mp4#xywh=0,0,20,20");
    MediaFragmentURI uri2 = new MediaFragmentURI("http://example.org/video.mp4#xywh=10,10,20,20");

    SpatialFragment r1 = uri1.getMediaFragment().getSpatialFragment();
    SpatialFragment r2 = uri2.getMediaFragment().getSpatialFragment();

    System.out.println(r1.overlaps(r2)); //prints 'true'

    System.out.println(r1.getBoundingBox(r2)); //prints 'xywh=0,0,30,30'

    System.out.println(r1.getIntersection(r2)); //prints 'xywh=10,10,10,10'

```

Media Fragment Utils
--------------------

```java

    MediaFragmentURI uri1 = new MediaFragmentURI("http://example.org/video.mp4#t=10,20&xywh=0,0,20,20");
    MediaFragmentURI uri2 = new MediaFragmentURI("http://example.org/video.mp4#t=30,40&xywh=percent:10,10,20,20");

    System.out.println(MediaFragments.spatialComparable( uri1, uri2 )); //returns 'false'

    System.out.println(MediaFragments.temporalComparable( uri1, uri2 )); //returns 'true'

```

Contact
=======

**Thomas Kurz**

tkurz@apache.org
