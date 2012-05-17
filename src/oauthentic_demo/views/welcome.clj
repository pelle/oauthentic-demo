(ns oauthentic-demo.views.welcome
  (:require [oauthentic-demo.views.common :as common]
            [clj-http.client :as client]
            [noir.session :as s])
  (:use [noir.core :only [defpage custom-handler]]
        [oauthentic.ring]
        [noir.response :only [redirect]]))

(defpage "/" []
    (let []
         (common/layout
           [:div 
            [:h1 "Welcome to oauthentic-demo"]
              (if-let [user (s/get :user)]
                [:div [:p (str "Hello, " (:name user))]
                  [:a {:href "/logout"} "Logout"]]
                [:a {:href "/github"} "Login with Github"])]
           )))

(defpage "/logout" []
  (do
    (s/remove! :user)
    (redirect "/")))

(defn github-login [req]
  (let [ _ (prn req)
        token (:oauthentic-token req)
        user (:body (client/get "https://api.github.com/user" 
                                { :as :json :accept :json :oauth-token (:access-token token) })) ]

        (do 
          (s/put! :user user)
          (redirect "/" ))))

(def github-handler
  (oauthentic.ring/oauthentic-handler github-login error-handler 
      { :authorization-url "https://github.com/login/oauth/authorize" 
        :token-url "https://github.com/login/oauth/access_token"
        :client-id (get (System/getenv) "GITHUB_CLIENT_ID") 
        :client-secret (get (System/getenv) "GITHUB_CLIENT_SECRET") }))

(custom-handler "/github" {:as req} (github-handler req))