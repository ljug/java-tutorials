for file in *.md; do
    mv "$file" "$(basename "$file" .md).asciidoc"
done
