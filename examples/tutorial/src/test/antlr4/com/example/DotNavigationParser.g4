parser grammar DotNavigationParser;

options{tokenVocab=DotNavigationLexer;}

dotFile: HEADER linkedPages+ END_GRAPH;
admin: ADMIN;
page: PAGE admin?;
link: LINK page;
linkedPages: page link+;
