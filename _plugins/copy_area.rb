class CopyToClipboard < Liquid::Tag
  def initialize(tagName, markup, tokens)
    super

    @text = markup
  end

  def render(context)
    text = @text.strip.gsub(/^'|"/, '').gsub(/'|"$/, '')

    <<-HTML
<div class="copy-area__wrapper">
  <div class="copy-area">
    <div class="copy-area__text-field js-copy-area-text-field">#{text}</div>
  </div>
</div>
    HTML
  end

  Liquid::Template.register_tag "copyArea", self
end