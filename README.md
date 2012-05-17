# oauthentic-demo

A demo of [oauthentic](http://github.com/pelle/oauthentic) the light weight easy to use OAuth 2 client for Clojure.

This currently logs in via GitHub. It should be easy to customize it to any other OAuth 2 app.

[Access Demo](http://oauthentic.herokuapp.com/)

## Usage

You need to [register an application](https://github.com/settings/applications/new) first at GitHub.

You will receive a client_id and client_secret from GitHub.

Set these in the environment variables GITHUB_CLIENT_ID and GITHUB_CLIENT_SECRET.

```bash
lein deps
GITHUB_CLIENT_ID=... GITHUB_CLIENT_SECRET=... lein run
```

## Deploying to Heroku

You need to first create an app:

```bash
heroku create --stack cedar
heroku config:add GITHUB_CLIENT_ID=... GITHUB_CLIENT_SECRET=...
git push heroku
```

## License

Copyright (C) 2012 Pelle Braendgaard

Distributed under the Eclipse Public License, the same as Clojure.

