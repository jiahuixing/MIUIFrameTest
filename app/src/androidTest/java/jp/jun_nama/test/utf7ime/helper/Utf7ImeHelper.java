package jp.jun_nama.test.utf7ime.helper;

import com.beetstra.jutf7.CharsetProvider;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

public class Utf7ImeHelper {

    private static final Charset CHARSET_MODIFIED_UTF7 = new CharsetProvider().charsetForName("X-MODIFIED-UTF-7");

    public static String e(String text) {
        byte[] encoded = getBytes(text, CHARSET_MODIFIED_UTF7);
        return new String(encoded, Charset.forName("US-ASCII"));
    }

    public static byte[] getBytes(String input, Charset charset) {
        CharBuffer chars = CharBuffer.wrap(input.toCharArray());
        CharsetEncoder encoder = charset.newEncoder()
                .onMalformedInput(CodingErrorAction.REPLACE)
                .onUnmappableCharacter(CodingErrorAction.REPLACE);
        ByteBuffer buffer;
        buffer = encode(chars.asReadOnlyBuffer(), encoder);
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        return bytes;

    }

    private static ByteBuffer encode(CharBuffer in, CharsetEncoder encoder) {
        int length = (int) (in.remaining() * (double) encoder.averageBytesPerChar());
        ByteBuffer out = ByteBuffer.allocate(length);

        encoder.reset();
        CoderResult flushResult = null;

        while (flushResult != CoderResult.UNDERFLOW) {
            CoderResult encodeResult = encoder.encode(in, out, true);
            if (encodeResult == CoderResult.OVERFLOW) {
                out = allocateMore(out);
                continue;
            }

            flushResult = encoder.flush(out);
            if (flushResult == CoderResult.OVERFLOW) {
                out = allocateMore(out);
            }
        }

        out.flip();
        return out;
    }

    private static ByteBuffer allocateMore(ByteBuffer output) {
        if (output.capacity() == 0) {
            return ByteBuffer.allocate(1);
        }
        ByteBuffer result = ByteBuffer.allocate(output.capacity() * 2);
        output.flip();
        result.put(output);
        return result;
    }

}
