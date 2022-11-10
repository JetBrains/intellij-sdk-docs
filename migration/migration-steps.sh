# The file has sh extension for code highlighting.

# On macOS it is required to define the following for sed commands to work:
export LC_CTYPE=C
export LANG=C

# 01 - SYSTEM uri in ./ and topics/

find . -type f -maxdepth 1 -exec sed -i '' -e \
    's|SYSTEM "https://resources.jetbrains.com/stardust/|SYSTEM "https://helpserver.labs.jb.gg/help/schemas/mvp/|g' {} \;

find ./topics/ ./reference_guide/ -type f -iname '*.xml' -exec sed -i '' -e \
    's|SYSTEM "https://resources.jetbrains.com/stardust/|SYSTEM "https://helpserver.labs.jb.gg/help/schemas/mvp/|g' {} \;

find ./topics/ ./reference_guide/ -type f -iname '*.xml' -exec sed -i '' -e \
    's|xsi:noNamespaceSchemaLocation="https://resources.jetbrains.com/stardust/|xsi:noNamespaceSchemaLocation="https://helpserver.labs.jb.gg/help/schemas/mvp/|g' {} \;

# 02 - *.tree file
#    Known problems:
#    - id -> topic is not roubst
#    - show-structure-depth="2"
#    - <toc-element href="https://api.ktor.io" toc-title="API Docs"/>

find . -type f -maxdepth 1 -iname '*.tree' -exec sed -i '' -e \
    's|DOCTYPE product-profile|DOCTYPE instance-profile|g' {} \;

find . -type f -maxdepth 1 -iname '*.tree' -exec sed -i '' -r -e \
    's|(</?)product-profile|\1instance-profile|g' {} \;

# FIXME: relies on the `id` as the first attribute, works for ktor, but not robust at all

find . -type f -maxdepth 1 -iname '*.tree' -exec sed -i '' -r -e \
    's|<toc-element id="([^"]*)"|<toc-element topic="\1"|g' {} \;

# 03 - project.ihp

sed -i '' -r -e 's|(</?)product|\1instance|g' project.ihp

# 04 rename XML topics

for old in ./topics/*.xml; do
    base_name=`basename $old .xml`;
    find . -type f -maxdepth 1 -iname '*.tree' -exec sed -i '' -e 's|topic="'"$base_name"'.xml"|topic="'"$base_name"'.topic"|g' {} \;
    mv $old ./topics/`basename $old .xml`.topic;
done

# 05 MD topics - XML pieces

replaceXmlTagsInTopics() {
    find ./topics/ ./reference_guide/ -type f -exec sed -i '' -r -e \
        's^(</?)'"$1"'( |>)^\1'"$2"'\2^g' {} \;
}

replaceXmlTagsInTopics microformat tldr
replaceXmlTagsInTopics excerpt link-summary

# 06 includes

find ./topics/ ./reference_guide/ -type f -exec sed -i '' -r -e \
    's~<include ([^>]*)include-id="~<include \1element-id="~g' {} \;

find ./topics/ ./reference_guide/ -type f -exec sed -i '' -r -e \
    's~<include ([^>]*)src="([^.]*)\.xml"~<include \1src="\2\.topic"~g' {} \;

replaceXmlTagsInTopics chunk snippet

# 07 href's

find ./topics/ ./reference_guide/ -type f -exec sed -i '' -r -e \
    's~<a ([^>]*)href="([^.]*)\.xml"~<a \1href="\2\.topic"~g' {} \;

# 08 product -> instance

replaceXmlAttributeName() {
    find ./topics/ ./reference_guide/ -type f -exec sed -i '' -r -e \
        's~<'"$1"' ([^>]*)'"$2"'="~<'"$1"' \1'"$3"'="~g' {} \;
}

replaceXmlTagsInTopics for if
replaceXmlAttributeName if product instance

# 09 - assorted attributes

# FIXME: below assumes the single line but this is frequently false for <list>s in ktor-docs
replaceXmlAttributeName list type style
replaceXmlTagsInTopics menupath ui-path

# more on includes, but can;t be moved up without moving the function
replaceXmlAttributeName include src from

# 10 - code / code-block's

# first backup non-block code's

alien="mg-729351-code-xsd";

find ./topics/ ./reference_guide/ -type f -exec sed -i '' -r -e \
    's~<code>([^<]*)</code>~<'"$alien"'>\1</'"$alien"'>~g' {} \;

find ./topics/ ./reference_guide/ -type f -exec sed -i '' -r -e \
    's~<code([^>]*) style="block"~<code-block\1~g' {} \;

# below is just a check, it SHOULD return nothing at this point,
# if it has found something, please let MG know

grep -R "<code " topics/ ./reference_guide/

# and thus, all closing </code>'s belong to opening <code-block>'s

find ./topics/ ./reference_guide/ -type f -exec sed -i '' -e \
    's~</code>~</code-block>~g' {} \;

# now, restore non-block codes

replaceXmlTagsInTopics $alien code

replaceXmlAttributeName code-block lines include-lines

# 11 - initial-collapse-state -> default-state

replaceXmlAttributeName code-block initial-collapse-state default-state
replaceXmlAttributeName chapter initial-collapse-state default-state
replaceXmlAttributeName procedure initial-collapse-state default-state

# 12 - Fix callouts

# delete {type="tip"}
# we can't delete empty line before as not every callout has it
find ./topics/ ./reference_guide/ -type f -exec sed -i '' -e \
    '/{type=\"tip\"}/d' {} \;

# replace {type="note"} with {style="note"}
find ./topics/ ./reference_guide/ -type f -exec sed -i '' -e \
    's~{type="note"}~{style="note"}~g' {} \;

# replace {type="warning"} with {style="warning"}
find ./topics/ ./reference_guide/ -type f -exec sed -i '' -e \
    's~{type="warning"}~{style="warning"}~g' {} \;
