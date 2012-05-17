(ns oauthentic-demo.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css html5]]))

(defpartial layout [& content]
            (html5
              [:head
               [:title "oauthentic-demo"]
               ]
              [:body
               [:div#wrapper
                content]]))
