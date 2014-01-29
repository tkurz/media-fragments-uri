Media Fragments URI 1.0 for Java
=================================

This is a Java implementation of W3C's Media Fragments URI 1.0 specification (http://www.w3.org/TR/media-frags/).
It is based on W3C's Parser http://www.w3.org/2008/WebVideo/Fragments/code/grammar/FragmentGrammar.jj

Example
=======

The library can be used to parse and serialize media fragments. Here are some examples, for more information look at the
tests at [MediaFragmentURITest.class](blob/master/src/test/java/com/github/tkurz/media/fragments/MediaFragmentURITest.java).

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

    if(uri.getMediaFragment().hasRegionalFragment()) {

        double w = uri.getMediaFragment().getRegionalFragment().getW();

        RegionalFragment.Unit unit = uri.getMediaFragment().getRegionalFragment().getUnit();

    }

```

Serializing Media Fragments
---------------------------

```java

    MediaFragmentURI uri = new MediaFragmentURI("http://example.org/video.mp4");

    uri.getMediaFragment().setRegionalFragment(new RegionalFragment(10,20,30,40));

    uri.getMediaFragment().setTemporalFragment(new NPTFragment(Clocktime.ZERO,new Clocktime(10)));

    System.out.println(uri); // prints 'http://example.org/video.mp4#xywh=pixel:10.0,20.0,30.0,40.0&t=,10.0'

```

Contact
=======

**Thomas Kurz**

thomas.kurz@salzburgresearch.at