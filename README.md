# TrntList

https://gitgud.io/drewbitt/trntlist

Project in an Android class as UI over mock JSON of torrent files.
You can locally add announce urls to torrent files and view the layout of their json.

Expects:

* Some sort of REST API (using mock json-server with docker https://github.com/clue/docker-json-server)
  * Returns object list of json torrent files in the format from https://github.com/slang800/json-torrent

Additional options for future implementations:

* Use API to POST files and get torrent files converted to json back - using simple https://gist.github.com/drewbitt/25e6416ac73d4ede4b06129c428c562c in a docker container
  * A quick addition to this is to add a flag for converting json -> torrent file and then sendFile, allowing for the json to be downloaded as a torrent file.
* Update json on server instead of only local
* Edit announces instead of only add
