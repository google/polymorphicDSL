// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/java/com/pdsl/reports/proto/StrategicReportData.proto

package com.pdsl.reports.proto;

/**
 * Protobuf type {@code com.pdsl.reports.proto.TacticalReportData}
 */
public  final class TacticalReportData extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.pdsl.reports.proto.TacticalReportData)
    TacticalReportDataOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TacticalReportData.newBuilder() to construct.
  private TacticalReportData(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TacticalReportData() {
    unfilteredPhraseBody_ = com.google.protobuf.LazyStringArrayList.EMPTY;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TacticalReportData(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              unfilteredPhraseBody_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000001;
            }
            unfilteredPhraseBody_.add(s);
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              contextToTestCases_ = com.google.protobuf.MapField.newMapField(
                  ContextToTestCasesDefaultEntryHolder.defaultEntry);
              mutable_bitField0_ |= 0x00000002;
            }
            com.google.protobuf.MapEntry<java.lang.String, com.pdsl.reports.proto.TestCaseGroup>
            contextToTestCases__ = input.readMessage(
                ContextToTestCasesDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
            contextToTestCases_.getMutableMap().put(
                contextToTestCases__.getKey(), contextToTestCases__.getValue());
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        unfilteredPhraseBody_ = unfilteredPhraseBody_.getUnmodifiableView();
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.pdsl.reports.proto.StrategicReportDataOuterClass.internal_static_com_pdsl_reports_proto_TacticalReportData_descriptor;
  }

  @SuppressWarnings({"rawtypes"})
  @java.lang.Override
  protected com.google.protobuf.MapField internalGetMapField(
      int number) {
    switch (number) {
      case 2:
        return internalGetContextToTestCases();
      default:
        throw new RuntimeException(
            "Invalid map field number: " + number);
    }
  }
  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.pdsl.reports.proto.StrategicReportDataOuterClass.internal_static_com_pdsl_reports_proto_TacticalReportData_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.pdsl.reports.proto.TacticalReportData.class, com.pdsl.reports.proto.TacticalReportData.Builder.class);
  }

  public static final int UNFILTERED_PHRASE_BODY_FIELD_NUMBER = 1;
  private com.google.protobuf.LazyStringList unfilteredPhraseBody_;
  /**
   * <code>repeated string unfiltered_phrase_body = 1;</code>
   */
  public com.google.protobuf.ProtocolStringList
      getUnfilteredPhraseBodyList() {
    return unfilteredPhraseBody_;
  }
  /**
   * <code>repeated string unfiltered_phrase_body = 1;</code>
   */
  public int getUnfilteredPhraseBodyCount() {
    return unfilteredPhraseBody_.size();
  }
  /**
   * <code>repeated string unfiltered_phrase_body = 1;</code>
   */
  public java.lang.String getUnfilteredPhraseBody(int index) {
    return unfilteredPhraseBody_.get(index);
  }
  /**
   * <code>repeated string unfiltered_phrase_body = 1;</code>
   */
  public com.google.protobuf.ByteString
      getUnfilteredPhraseBodyBytes(int index) {
    return unfilteredPhraseBody_.getByteString(index);
  }

  public static final int CONTEXT_TO_TEST_CASES_FIELD_NUMBER = 2;
  private static final class ContextToTestCasesDefaultEntryHolder {
    static final com.google.protobuf.MapEntry<
        java.lang.String, com.pdsl.reports.proto.TestCaseGroup> defaultEntry =
            com.google.protobuf.MapEntry
            .newDefaultInstance(
                com.pdsl.reports.proto.StrategicReportDataOuterClass.internal_static_com_pdsl_reports_proto_TacticalReportData_ContextToTestCasesEntry_descriptor, 
                com.google.protobuf.WireFormat.FieldType.STRING,
                "",
                com.google.protobuf.WireFormat.FieldType.MESSAGE,
                com.pdsl.reports.proto.TestCaseGroup.getDefaultInstance());
  }
  private com.google.protobuf.MapField<
      java.lang.String, com.pdsl.reports.proto.TestCaseGroup> contextToTestCases_;
  private com.google.protobuf.MapField<java.lang.String, com.pdsl.reports.proto.TestCaseGroup>
  internalGetContextToTestCases() {
    if (contextToTestCases_ == null) {
      return com.google.protobuf.MapField.emptyMapField(
          ContextToTestCasesDefaultEntryHolder.defaultEntry);
    }
    return contextToTestCases_;
  }

  public int getContextToTestCasesCount() {
    return internalGetContextToTestCases().getMap().size();
  }
  /**
   * <code>map&lt;string, .com.pdsl.reports.proto.TestCaseGroup&gt; context_to_test_cases = 2;</code>
   */

  public boolean containsContextToTestCases(
      java.lang.String key) {
    if (key == null) { throw new java.lang.NullPointerException(); }
    return internalGetContextToTestCases().getMap().containsKey(key);
  }
  /**
   * Use {@link #getContextToTestCasesMap()} instead.
   */
  @java.lang.Deprecated
  public java.util.Map<java.lang.String, com.pdsl.reports.proto.TestCaseGroup> getContextToTestCases() {
    return getContextToTestCasesMap();
  }
  /**
   * <code>map&lt;string, .com.pdsl.reports.proto.TestCaseGroup&gt; context_to_test_cases = 2;</code>
   */

  public java.util.Map<java.lang.String, com.pdsl.reports.proto.TestCaseGroup> getContextToTestCasesMap() {
    return internalGetContextToTestCases().getMap();
  }
  /**
   * <code>map&lt;string, .com.pdsl.reports.proto.TestCaseGroup&gt; context_to_test_cases = 2;</code>
   */

  public com.pdsl.reports.proto.TestCaseGroup getContextToTestCasesOrDefault(
      java.lang.String key,
      com.pdsl.reports.proto.TestCaseGroup defaultValue) {
    if (key == null) { throw new java.lang.NullPointerException(); }
    java.util.Map<java.lang.String, com.pdsl.reports.proto.TestCaseGroup> map =
        internalGetContextToTestCases().getMap();
    return map.containsKey(key) ? map.get(key) : defaultValue;
  }
  /**
   * <code>map&lt;string, .com.pdsl.reports.proto.TestCaseGroup&gt; context_to_test_cases = 2;</code>
   */

  public com.pdsl.reports.proto.TestCaseGroup getContextToTestCasesOrThrow(
      java.lang.String key) {
    if (key == null) { throw new java.lang.NullPointerException(); }
    java.util.Map<java.lang.String, com.pdsl.reports.proto.TestCaseGroup> map =
        internalGetContextToTestCases().getMap();
    if (!map.containsKey(key)) {
      throw new java.lang.IllegalArgumentException();
    }
    return map.get(key);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < unfilteredPhraseBody_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, unfilteredPhraseBody_.getRaw(i));
    }
    com.google.protobuf.GeneratedMessageV3
      .serializeStringMapTo(
        output,
        internalGetContextToTestCases(),
        ContextToTestCasesDefaultEntryHolder.defaultEntry,
        2);
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < unfilteredPhraseBody_.size(); i++) {
        dataSize += computeStringSizeNoTag(unfilteredPhraseBody_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getUnfilteredPhraseBodyList().size();
    }
    for (java.util.Map.Entry<java.lang.String, com.pdsl.reports.proto.TestCaseGroup> entry
         : internalGetContextToTestCases().getMap().entrySet()) {
      com.google.protobuf.MapEntry<java.lang.String, com.pdsl.reports.proto.TestCaseGroup>
      contextToTestCases__ = ContextToTestCasesDefaultEntryHolder.defaultEntry.newBuilderForType()
          .setKey(entry.getKey())
          .setValue(entry.getValue())
          .build();
      size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(2, contextToTestCases__);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.pdsl.reports.proto.TacticalReportData)) {
      return super.equals(obj);
    }
    com.pdsl.reports.proto.TacticalReportData other = (com.pdsl.reports.proto.TacticalReportData) obj;

    boolean result = true;
    result = result && getUnfilteredPhraseBodyList()
        .equals(other.getUnfilteredPhraseBodyList());
    result = result && internalGetContextToTestCases().equals(
        other.internalGetContextToTestCases());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getUnfilteredPhraseBodyCount() > 0) {
      hash = (37 * hash) + UNFILTERED_PHRASE_BODY_FIELD_NUMBER;
      hash = (53 * hash) + getUnfilteredPhraseBodyList().hashCode();
    }
    if (!internalGetContextToTestCases().getMap().isEmpty()) {
      hash = (37 * hash) + CONTEXT_TO_TEST_CASES_FIELD_NUMBER;
      hash = (53 * hash) + internalGetContextToTestCases().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.pdsl.reports.proto.TacticalReportData parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.pdsl.reports.proto.TacticalReportData parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.pdsl.reports.proto.TacticalReportData parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.pdsl.reports.proto.TacticalReportData parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.pdsl.reports.proto.TacticalReportData parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.pdsl.reports.proto.TacticalReportData parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.pdsl.reports.proto.TacticalReportData parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.pdsl.reports.proto.TacticalReportData parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.pdsl.reports.proto.TacticalReportData parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.pdsl.reports.proto.TacticalReportData parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.pdsl.reports.proto.TacticalReportData parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.pdsl.reports.proto.TacticalReportData parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.pdsl.reports.proto.TacticalReportData prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.pdsl.reports.proto.TacticalReportData}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.pdsl.reports.proto.TacticalReportData)
      com.pdsl.reports.proto.TacticalReportDataOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.pdsl.reports.proto.StrategicReportDataOuterClass.internal_static_com_pdsl_reports_proto_TacticalReportData_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 2:
          return internalGetContextToTestCases();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMutableMapField(
        int number) {
      switch (number) {
        case 2:
          return internalGetMutableContextToTestCases();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.pdsl.reports.proto.StrategicReportDataOuterClass.internal_static_com_pdsl_reports_proto_TacticalReportData_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.pdsl.reports.proto.TacticalReportData.class, com.pdsl.reports.proto.TacticalReportData.Builder.class);
    }

    // Construct using com.pdsl.reports.proto.TacticalReportData.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      unfilteredPhraseBody_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      internalGetMutableContextToTestCases().clear();
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.pdsl.reports.proto.StrategicReportDataOuterClass.internal_static_com_pdsl_reports_proto_TacticalReportData_descriptor;
    }

    @java.lang.Override
    public com.pdsl.reports.proto.TacticalReportData getDefaultInstanceForType() {
      return com.pdsl.reports.proto.TacticalReportData.getDefaultInstance();
    }

    @java.lang.Override
    public com.pdsl.reports.proto.TacticalReportData build() {
      com.pdsl.reports.proto.TacticalReportData result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.pdsl.reports.proto.TacticalReportData buildPartial() {
      com.pdsl.reports.proto.TacticalReportData result = new com.pdsl.reports.proto.TacticalReportData(this);
      int from_bitField0_ = bitField0_;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        unfilteredPhraseBody_ = unfilteredPhraseBody_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.unfilteredPhraseBody_ = unfilteredPhraseBody_;
      result.contextToTestCases_ = internalGetContextToTestCases();
      result.contextToTestCases_.makeImmutable();
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.pdsl.reports.proto.TacticalReportData) {
        return mergeFrom((com.pdsl.reports.proto.TacticalReportData)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.pdsl.reports.proto.TacticalReportData other) {
      if (other == com.pdsl.reports.proto.TacticalReportData.getDefaultInstance()) return this;
      if (!other.unfilteredPhraseBody_.isEmpty()) {
        if (unfilteredPhraseBody_.isEmpty()) {
          unfilteredPhraseBody_ = other.unfilteredPhraseBody_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureUnfilteredPhraseBodyIsMutable();
          unfilteredPhraseBody_.addAll(other.unfilteredPhraseBody_);
        }
        onChanged();
      }
      internalGetMutableContextToTestCases().mergeFrom(
          other.internalGetContextToTestCases());
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.pdsl.reports.proto.TacticalReportData parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.pdsl.reports.proto.TacticalReportData) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private com.google.protobuf.LazyStringList unfilteredPhraseBody_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureUnfilteredPhraseBodyIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        unfilteredPhraseBody_ = new com.google.protobuf.LazyStringArrayList(unfilteredPhraseBody_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated string unfiltered_phrase_body = 1;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getUnfilteredPhraseBodyList() {
      return unfilteredPhraseBody_.getUnmodifiableView();
    }
    /**
     * <code>repeated string unfiltered_phrase_body = 1;</code>
     */
    public int getUnfilteredPhraseBodyCount() {
      return unfilteredPhraseBody_.size();
    }
    /**
     * <code>repeated string unfiltered_phrase_body = 1;</code>
     */
    public java.lang.String getUnfilteredPhraseBody(int index) {
      return unfilteredPhraseBody_.get(index);
    }
    /**
     * <code>repeated string unfiltered_phrase_body = 1;</code>
     */
    public com.google.protobuf.ByteString
        getUnfilteredPhraseBodyBytes(int index) {
      return unfilteredPhraseBody_.getByteString(index);
    }
    /**
     * <code>repeated string unfiltered_phrase_body = 1;</code>
     */
    public Builder setUnfilteredPhraseBody(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureUnfilteredPhraseBodyIsMutable();
      unfilteredPhraseBody_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string unfiltered_phrase_body = 1;</code>
     */
    public Builder addUnfilteredPhraseBody(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureUnfilteredPhraseBodyIsMutable();
      unfilteredPhraseBody_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string unfiltered_phrase_body = 1;</code>
     */
    public Builder addAllUnfilteredPhraseBody(
        java.lang.Iterable<java.lang.String> values) {
      ensureUnfilteredPhraseBodyIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, unfilteredPhraseBody_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string unfiltered_phrase_body = 1;</code>
     */
    public Builder clearUnfilteredPhraseBody() {
      unfilteredPhraseBody_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string unfiltered_phrase_body = 1;</code>
     */
    public Builder addUnfilteredPhraseBodyBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensureUnfilteredPhraseBodyIsMutable();
      unfilteredPhraseBody_.add(value);
      onChanged();
      return this;
    }

    private com.google.protobuf.MapField<
        java.lang.String, com.pdsl.reports.proto.TestCaseGroup> contextToTestCases_;
    private com.google.protobuf.MapField<java.lang.String, com.pdsl.reports.proto.TestCaseGroup>
    internalGetContextToTestCases() {
      if (contextToTestCases_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            ContextToTestCasesDefaultEntryHolder.defaultEntry);
      }
      return contextToTestCases_;
    }
    private com.google.protobuf.MapField<java.lang.String, com.pdsl.reports.proto.TestCaseGroup>
    internalGetMutableContextToTestCases() {
      onChanged();
      if (contextToTestCases_ == null) {
        contextToTestCases_ = com.google.protobuf.MapField.newMapField(
            ContextToTestCasesDefaultEntryHolder.defaultEntry);
      }
      if (!contextToTestCases_.isMutable()) {
        contextToTestCases_ = contextToTestCases_.copy();
      }
      return contextToTestCases_;
    }

    public int getContextToTestCasesCount() {
      return internalGetContextToTestCases().getMap().size();
    }
    /**
     * <code>map&lt;string, .com.pdsl.reports.proto.TestCaseGroup&gt; context_to_test_cases = 2;</code>
     */

    public boolean containsContextToTestCases(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      return internalGetContextToTestCases().getMap().containsKey(key);
    }
    /**
     * Use {@link #getContextToTestCasesMap()} instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, com.pdsl.reports.proto.TestCaseGroup> getContextToTestCases() {
      return getContextToTestCasesMap();
    }
    /**
     * <code>map&lt;string, .com.pdsl.reports.proto.TestCaseGroup&gt; context_to_test_cases = 2;</code>
     */

    public java.util.Map<java.lang.String, com.pdsl.reports.proto.TestCaseGroup> getContextToTestCasesMap() {
      return internalGetContextToTestCases().getMap();
    }
    /**
     * <code>map&lt;string, .com.pdsl.reports.proto.TestCaseGroup&gt; context_to_test_cases = 2;</code>
     */

    public com.pdsl.reports.proto.TestCaseGroup getContextToTestCasesOrDefault(
        java.lang.String key,
        com.pdsl.reports.proto.TestCaseGroup defaultValue) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      java.util.Map<java.lang.String, com.pdsl.reports.proto.TestCaseGroup> map =
          internalGetContextToTestCases().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <code>map&lt;string, .com.pdsl.reports.proto.TestCaseGroup&gt; context_to_test_cases = 2;</code>
     */

    public com.pdsl.reports.proto.TestCaseGroup getContextToTestCasesOrThrow(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      java.util.Map<java.lang.String, com.pdsl.reports.proto.TestCaseGroup> map =
          internalGetContextToTestCases().getMap();
      if (!map.containsKey(key)) {
        throw new java.lang.IllegalArgumentException();
      }
      return map.get(key);
    }

    public Builder clearContextToTestCases() {
      internalGetMutableContextToTestCases().getMutableMap()
          .clear();
      return this;
    }
    /**
     * <code>map&lt;string, .com.pdsl.reports.proto.TestCaseGroup&gt; context_to_test_cases = 2;</code>
     */

    public Builder removeContextToTestCases(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      internalGetMutableContextToTestCases().getMutableMap()
          .remove(key);
      return this;
    }
    /**
     * Use alternate mutation accessors instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, com.pdsl.reports.proto.TestCaseGroup>
    getMutableContextToTestCases() {
      return internalGetMutableContextToTestCases().getMutableMap();
    }
    /**
     * <code>map&lt;string, .com.pdsl.reports.proto.TestCaseGroup&gt; context_to_test_cases = 2;</code>
     */
    public Builder putContextToTestCases(
        java.lang.String key,
        com.pdsl.reports.proto.TestCaseGroup value) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      if (value == null) { throw new java.lang.NullPointerException(); }
      internalGetMutableContextToTestCases().getMutableMap()
          .put(key, value);
      return this;
    }
    /**
     * <code>map&lt;string, .com.pdsl.reports.proto.TestCaseGroup&gt; context_to_test_cases = 2;</code>
     */

    public Builder putAllContextToTestCases(
        java.util.Map<java.lang.String, com.pdsl.reports.proto.TestCaseGroup> values) {
      internalGetMutableContextToTestCases().getMutableMap()
          .putAll(values);
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.pdsl.reports.proto.TacticalReportData)
  }

  // @@protoc_insertion_point(class_scope:com.pdsl.reports.proto.TacticalReportData)
  private static final com.pdsl.reports.proto.TacticalReportData DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.pdsl.reports.proto.TacticalReportData();
  }

  public static com.pdsl.reports.proto.TacticalReportData getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TacticalReportData>
      PARSER = new com.google.protobuf.AbstractParser<TacticalReportData>() {
    @java.lang.Override
    public TacticalReportData parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TacticalReportData(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TacticalReportData> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TacticalReportData> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.pdsl.reports.proto.TacticalReportData getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

