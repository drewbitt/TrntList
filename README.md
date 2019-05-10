# TrrntZip
https://gitgud.io/drewbitt/trntlist

Project in an Android class as UI over mock JSON.

Expects:
* Some sort of REST API (using mock json-server with docker https://github.com/clue/docker-json-server)
    * Returns object list of json torrent files in the format from https://github.com/slang800/json-torrent
* API to POST files and get torrent files converted to json back - using simple https://gist.github.com/drewbitt/25e6416ac73d4ede4b06129c428c562c