package org.dreamwork.tools.sherpa.wrapper;

import com.k2fsa.sherpa.ncnn.*;

public class SherpaHelper {
    private static int THREAD_NUMBERS = 3;

    private static String MODEL_ROOT;

    public static SherpaNcnn initRecognizer (SherpaConfig conf) {
        System.out.println ("initialing recognizer ...");
        MODEL_ROOT = conf.basedir;
        if (conf.threads <= 0) {
            int count = Runtime.getRuntime ().availableProcessors ();
            THREAD_NUMBERS = count / 2;
            if (THREAD_NUMBERS < 1) {
                THREAD_NUMBERS = 1;
            }
        }

        // configurations
        FeatureExtractorConfig feature = new FeatureExtractorConfig (conf.sampleRate, conf.featureDim);
        DecoderConfig decoder = new DecoderConfig (conf.decoderMethod, conf.activePaths);
        ModelConfig model = null;
        for (ModelType type : ModelType.values ()) {
            if (type.name ().equals (conf.modelName)) {
                // pre-defined model name
                model = getModelConfig (conf.modelName, conf.useGpu);
                break;
            }
        }
        if (model == null) {
            model = createModelConfig (conf);
        }

        RecognizerConfig recognizer = new RecognizerConfig (
                feature, model, decoder,
                conf.endpointEnabled, conf.r1Silence, conf.r2Silence, conf.r3utterance,
                conf.hotWordsFile, conf.hotWordScore
        );
        return new SherpaNcnn (recognizer);
    }

    public static ModelConfig getModelConfig (String type, boolean useGPU) {
        return getModelConfig (ModelType.of (type), useGPU);
    }

    public static ModelConfig getModelConfig (ModelType type, boolean useGPU) {
        switch (type) {
            case Default:
                return createDefaultModelConfig (useGPU);
            case Transducer:
                return createTransducerModelConfig (useGPU);
            case BilingualZhEn:
                return createBilingualZhEnModelConfig (useGPU);
            case StreamingZipFormerEn:
                return createStreamingZipFormerEnModelConfig (useGPU);
            case StreamingZipFormerFr:
                return createStreamingZipFormerFrModelConfig (useGPU);
            case StreamingZipFormerBilingualZhEn:
                return createStreamingZipFormerBilingualZhEnModelConfig (useGPU);
            case StreamingZipFormerSmallBilingualZhEn:
                return createStreamingZipFormerSmallBilingualZhEnModelConfig (useGPU);

            default:
                return null;
        }
    }

    // type = 0
    private static ModelConfig createDefaultModelConfig (boolean useGPU) {
        return createModelConfig ("sherpa-ncnn-2022-09-30", useGPU);
    }

    // type = 1
    private static ModelConfig createTransducerModelConfig (boolean useGPU) {
        String modelDir = MODEL_ROOT + "/sherpa-ncnn-conv-emformer-transducer-2022-12-06";
        ModelConfig config = new ModelConfig ();
        config.setEncoderParam (modelDir + "/encoder_jit_trace-pnnx.ncnn.int8.param");
        config.setEncoderBin (modelDir + "/encoder_jit_trace-pnnx.ncnn.int8.bin");
        config.setDecoderParam (modelDir + "/decoder_jit_trace-pnnx.ncnn.param");
        config.setDecoderBin (modelDir + "/decoder_jit_trace-pnnx.ncnn.bin");
        config.setJoinerParam (modelDir + "/joiner_jit_trace-pnnx.ncnn.int8.param");
        config.setJoinerBin (modelDir + "/joiner_jit_trace-pnnx.ncnn.int8.bin");
        config.setTokens (modelDir + "/tokens.txt");
        config.setNumThreads (THREAD_NUMBERS);
        config.setUseGPU (useGPU);

        return config;
    }

    // type = 2
    private static ModelConfig createBilingualZhEnModelConfig (boolean useGPU) {
        return createModelConfig ("sherpa-ncnn-streaming-zipformer-bilingual-zh-en-2023-02-13", useGPU);
    }

    // type =3
    private static ModelConfig createStreamingZipFormerEnModelConfig (boolean useGPU) {
        return createModelConfig ("sherpa-ncnn-streaming-zipformer-en-2023-02-13", useGPU);
    }

    // type = 4
    private static ModelConfig createStreamingZipFormerFrModelConfig (boolean useGPU) {
        return createModelConfig ("sherpa-ncnn-streaming-zipformer-fr-2023-04-14", useGPU);
    }

    // type = 5
    private static ModelConfig createStreamingZipFormerBilingualZhEnModelConfig (boolean useGPU) {
        return createModelConfig ("sherpa-ncnn-streaming-zipformer-bilingual-zh-en-2023-02-13", useGPU);
    }

    // type = 6
    private static ModelConfig createStreamingZipFormerSmallBilingualZhEnModelConfig (boolean useGPU) {
        return createModelConfig ("sherpa-ncnn-streaming-zipformer-small-bilingual-zh-en-2023-02-16", useGPU);
    }

    private static ModelConfig createModelConfig (String name, boolean useGPU) {
        ModelConfig config = new ModelConfig ();
        name = MODEL_ROOT + '/' + name;
        config.setEncoderParam (name + "/encoder_jit_trace-pnnx.ncnn.param");
        config.setEncoderBin (name + "/encoder_jit_trace-pnnx.ncnn.bin");
        config.setDecoderParam (name + "/decoder_jit_trace-pnnx.ncnn.param");
        config.setDecoderBin (name + "/decoder_jit_trace-pnnx.ncnn.bin");
        config.setJoinerParam (name + "/joiner_jit_trace-pnnx.ncnn.param");
        config.setJoinerBin (name + "/joiner_jit_trace-pnnx.ncnn.bin");
        config.setTokens (name + "/tokens.txt");
        config.setNumThreads (THREAD_NUMBERS);
        config.setUseGPU (useGPU);
        System.out.printf ("asr will work on %d threads.%n", THREAD_NUMBERS);
        return config;
    }

    private static ModelConfig createModelConfig (SherpaConfig conf) {
        String prefix = conf.basedir + "/" + conf.modelName + "/";
        ModelConfig config = new ModelConfig ();
        config.setEncoderParam (prefix + conf.encoderParam);
        config.setEncoderBin (prefix + conf.encoderBin);
        config.setDecoderParam (prefix + conf.decoderParam);
        config.setDecoderBin (prefix + conf.decoderBin);
        config.setJoinerParam (prefix + conf.joinerParam);
        config.setJoinerBin (prefix + conf.joinerBin);
        config.setTokens (prefix + conf.tokens);
        config.setNumThreads (conf.threads);
        config.setUseGPU (conf.useGpu);
        System.out.printf ("asr will work on %d threads.%n", conf.threads);
        return config;
    }
}