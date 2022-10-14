---
layout: default
title: Crease Pattern (.cp)
nav_order: 3
---

# Crease Pattern (`.cp`) Files

This library can also read and write `.cp` files. This is a ubiquitous format for origami crease patterns.

The Crease Pattern file format is very simple, but also limited to just mountain, valley, edge and auxiliary lines. This file format is supported by programs like [Oriedita](https://oriedita.github.io), [Oripa](https://github.com/oripa/oripa), [OrigamiDraw](https://apps.apple.com/us/app/origamidraw/id1268158815), [Orihime](http://mt777.html.xdomain.jp/) and others.

## Usage

Use the [`fold.io.CreasePatternReader`](./apidocs/fold/io/CreasePatternReader.html) and [`fold.io.CreasePatternWriter`](./apidocs/fold/io/CreasePatternWriter.html) classes to read and write files in the Crease Pattern format.

