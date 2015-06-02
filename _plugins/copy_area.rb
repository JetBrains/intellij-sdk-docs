class CopyToClipboard < Liquid::Tag
  def initialize(tagName, markup, tokens)
    super

    @text = markup
  end

  def render(context)
    text = @text.strip.gsub(/^'|"/, '').gsub(/'|"$/, '')

    <<-HTML
<div class="copy-area">
    <input class="copy-area__field" value="#{text}">
    <button class="btn copy-area__button" data-clipboard-text="#{text}">Copy</button>
</div>
    HTML
  end

  Liquid::Template.register_tag "copyArea", self
end