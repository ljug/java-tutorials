for file in *.asciidoc; do
    mv "$file" "$(basename "$file" .asciidoc).md"
done
