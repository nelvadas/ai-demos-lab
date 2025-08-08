package io.nono.ia;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;

public record Paragraph(String filename, TextSegment text, Embedding embedding) {
}
