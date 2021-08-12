// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/java/com/pdsl/reports/proto/StrategicReportData.proto

package com.pdsl.reports.proto;

/**
 * Protobuf type {@code com.pdsl.reports.proto.OperationalReportData}
 */
public  final class OperationalReportData extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.pdsl.reports.proto.OperationalReportData)
    OperationalReportDataOrBuilder {
private static final long serialVersionUID = 0L;
  // Use OperationalReportData.newBuilder() to construct.
  private OperationalReportData(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private OperationalReportData() {
    application_ = "";
    tacticalReportData_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private OperationalReportData(
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

            application_ = s;
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              tacticalReportData_ = new java.util.ArrayList<com.pdsl.reports.proto.TacticalReportData>();
              mutable_bitField0_ |= 0x00000002;
            }
            tacticalReportData_.add(
                input.readMessage(com.pdsl.reports.proto.TacticalReportData.parser(), extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
        tacticalReportData_ = java.util.Collections.unmodifiableList(tacticalReportData_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.pdsl.reports.proto.StrategicReportDataOuterClass.internal_static_com_pdsl_reports_proto_OperationalReportData_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.pdsl.reports.proto.StrategicReportDataOuterClass.internal_static_com_pdsl_reports_proto_OperationalReportData_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.pdsl.reports.proto.OperationalReportData.class, com.pdsl.reports.proto.OperationalReportData.Builder.class);
  }

  private int bitField0_;
  public static final int APPLICATION_FIELD_NUMBER = 1;
  private volatile java.lang.Object application_;
  /**
   * <code>string application = 1;</code>
   */
  public java.lang.String getApplication() {
    java.lang.Object ref = application_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      application_ = s;
      return s;
    }
  }
  /**
   * <code>string application = 1;</code>
   */
  public com.google.protobuf.ByteString
      getApplicationBytes() {
    java.lang.Object ref = application_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      application_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TACTICAL_REPORT_DATA_FIELD_NUMBER = 2;
  private java.util.List<com.pdsl.reports.proto.TacticalReportData> tacticalReportData_;
  /**
   * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
   */
  public java.util.List<com.pdsl.reports.proto.TacticalReportData> getTacticalReportDataList() {
    return tacticalReportData_;
  }
  /**
   * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
   */
  public java.util.List<? extends com.pdsl.reports.proto.TacticalReportDataOrBuilder> 
      getTacticalReportDataOrBuilderList() {
    return tacticalReportData_;
  }
  /**
   * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
   */
  public int getTacticalReportDataCount() {
    return tacticalReportData_.size();
  }
  /**
   * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
   */
  public com.pdsl.reports.proto.TacticalReportData getTacticalReportData(int index) {
    return tacticalReportData_.get(index);
  }
  /**
   * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
   */
  public com.pdsl.reports.proto.TacticalReportDataOrBuilder getTacticalReportDataOrBuilder(
      int index) {
    return tacticalReportData_.get(index);
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
    if (!getApplicationBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, application_);
    }
    for (int i = 0; i < tacticalReportData_.size(); i++) {
      output.writeMessage(2, tacticalReportData_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getApplicationBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, application_);
    }
    for (int i = 0; i < tacticalReportData_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, tacticalReportData_.get(i));
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
    if (!(obj instanceof com.pdsl.reports.proto.OperationalReportData)) {
      return super.equals(obj);
    }
    com.pdsl.reports.proto.OperationalReportData other = (com.pdsl.reports.proto.OperationalReportData) obj;

    boolean result = true;
    result = result && getApplication()
        .equals(other.getApplication());
    result = result && getTacticalReportDataList()
        .equals(other.getTacticalReportDataList());
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
    hash = (37 * hash) + APPLICATION_FIELD_NUMBER;
    hash = (53 * hash) + getApplication().hashCode();
    if (getTacticalReportDataCount() > 0) {
      hash = (37 * hash) + TACTICAL_REPORT_DATA_FIELD_NUMBER;
      hash = (53 * hash) + getTacticalReportDataList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.pdsl.reports.proto.OperationalReportData parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.pdsl.reports.proto.OperationalReportData parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.pdsl.reports.proto.OperationalReportData parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.pdsl.reports.proto.OperationalReportData parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.pdsl.reports.proto.OperationalReportData parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.pdsl.reports.proto.OperationalReportData parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.pdsl.reports.proto.OperationalReportData parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.pdsl.reports.proto.OperationalReportData parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.pdsl.reports.proto.OperationalReportData parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.pdsl.reports.proto.OperationalReportData parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.pdsl.reports.proto.OperationalReportData parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.pdsl.reports.proto.OperationalReportData parseFrom(
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
  public static Builder newBuilder(com.pdsl.reports.proto.OperationalReportData prototype) {
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
   * Protobuf type {@code com.pdsl.reports.proto.OperationalReportData}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.pdsl.reports.proto.OperationalReportData)
      com.pdsl.reports.proto.OperationalReportDataOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.pdsl.reports.proto.StrategicReportDataOuterClass.internal_static_com_pdsl_reports_proto_OperationalReportData_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.pdsl.reports.proto.StrategicReportDataOuterClass.internal_static_com_pdsl_reports_proto_OperationalReportData_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.pdsl.reports.proto.OperationalReportData.class, com.pdsl.reports.proto.OperationalReportData.Builder.class);
    }

    // Construct using com.pdsl.reports.proto.OperationalReportData.newBuilder()
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
        getTacticalReportDataFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      application_ = "";

      if (tacticalReportDataBuilder_ == null) {
        tacticalReportData_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        tacticalReportDataBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.pdsl.reports.proto.StrategicReportDataOuterClass.internal_static_com_pdsl_reports_proto_OperationalReportData_descriptor;
    }

    @java.lang.Override
    public com.pdsl.reports.proto.OperationalReportData getDefaultInstanceForType() {
      return com.pdsl.reports.proto.OperationalReportData.getDefaultInstance();
    }

    @java.lang.Override
    public com.pdsl.reports.proto.OperationalReportData build() {
      com.pdsl.reports.proto.OperationalReportData result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.pdsl.reports.proto.OperationalReportData buildPartial() {
      com.pdsl.reports.proto.OperationalReportData result = new com.pdsl.reports.proto.OperationalReportData(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.application_ = application_;
      if (tacticalReportDataBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          tacticalReportData_ = java.util.Collections.unmodifiableList(tacticalReportData_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.tacticalReportData_ = tacticalReportData_;
      } else {
        result.tacticalReportData_ = tacticalReportDataBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
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
      if (other instanceof com.pdsl.reports.proto.OperationalReportData) {
        return mergeFrom((com.pdsl.reports.proto.OperationalReportData)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.pdsl.reports.proto.OperationalReportData other) {
      if (other == com.pdsl.reports.proto.OperationalReportData.getDefaultInstance()) return this;
      if (!other.getApplication().isEmpty()) {
        application_ = other.application_;
        onChanged();
      }
      if (tacticalReportDataBuilder_ == null) {
        if (!other.tacticalReportData_.isEmpty()) {
          if (tacticalReportData_.isEmpty()) {
            tacticalReportData_ = other.tacticalReportData_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureTacticalReportDataIsMutable();
            tacticalReportData_.addAll(other.tacticalReportData_);
          }
          onChanged();
        }
      } else {
        if (!other.tacticalReportData_.isEmpty()) {
          if (tacticalReportDataBuilder_.isEmpty()) {
            tacticalReportDataBuilder_.dispose();
            tacticalReportDataBuilder_ = null;
            tacticalReportData_ = other.tacticalReportData_;
            bitField0_ = (bitField0_ & ~0x00000002);
            tacticalReportDataBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getTacticalReportDataFieldBuilder() : null;
          } else {
            tacticalReportDataBuilder_.addAllMessages(other.tacticalReportData_);
          }
        }
      }
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
      com.pdsl.reports.proto.OperationalReportData parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.pdsl.reports.proto.OperationalReportData) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object application_ = "";
    /**
     * <code>string application = 1;</code>
     */
    public java.lang.String getApplication() {
      java.lang.Object ref = application_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        application_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string application = 1;</code>
     */
    public com.google.protobuf.ByteString
        getApplicationBytes() {
      java.lang.Object ref = application_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        application_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string application = 1;</code>
     */
    public Builder setApplication(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      application_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string application = 1;</code>
     */
    public Builder clearApplication() {
      
      application_ = getDefaultInstance().getApplication();
      onChanged();
      return this;
    }
    /**
     * <code>string application = 1;</code>
     */
    public Builder setApplicationBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      application_ = value;
      onChanged();
      return this;
    }

    private java.util.List<com.pdsl.reports.proto.TacticalReportData> tacticalReportData_ =
      java.util.Collections.emptyList();
    private void ensureTacticalReportDataIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        tacticalReportData_ = new java.util.ArrayList<com.pdsl.reports.proto.TacticalReportData>(tacticalReportData_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.pdsl.reports.proto.TacticalReportData, com.pdsl.reports.proto.TacticalReportData.Builder, com.pdsl.reports.proto.TacticalReportDataOrBuilder> tacticalReportDataBuilder_;

    /**
     * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
     */
    public java.util.List<com.pdsl.reports.proto.TacticalReportData> getTacticalReportDataList() {
      if (tacticalReportDataBuilder_ == null) {
        return java.util.Collections.unmodifiableList(tacticalReportData_);
      } else {
        return tacticalReportDataBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
     */
    public int getTacticalReportDataCount() {
      if (tacticalReportDataBuilder_ == null) {
        return tacticalReportData_.size();
      } else {
        return tacticalReportDataBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
     */
    public com.pdsl.reports.proto.TacticalReportData getTacticalReportData(int index) {
      if (tacticalReportDataBuilder_ == null) {
        return tacticalReportData_.get(index);
      } else {
        return tacticalReportDataBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
     */
    public Builder setTacticalReportData(
        int index, com.pdsl.reports.proto.TacticalReportData value) {
      if (tacticalReportDataBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureTacticalReportDataIsMutable();
        tacticalReportData_.set(index, value);
        onChanged();
      } else {
        tacticalReportDataBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
     */
    public Builder setTacticalReportData(
        int index, com.pdsl.reports.proto.TacticalReportData.Builder builderForValue) {
      if (tacticalReportDataBuilder_ == null) {
        ensureTacticalReportDataIsMutable();
        tacticalReportData_.set(index, builderForValue.build());
        onChanged();
      } else {
        tacticalReportDataBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
     */
    public Builder addTacticalReportData(com.pdsl.reports.proto.TacticalReportData value) {
      if (tacticalReportDataBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureTacticalReportDataIsMutable();
        tacticalReportData_.add(value);
        onChanged();
      } else {
        tacticalReportDataBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
     */
    public Builder addTacticalReportData(
        int index, com.pdsl.reports.proto.TacticalReportData value) {
      if (tacticalReportDataBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureTacticalReportDataIsMutable();
        tacticalReportData_.add(index, value);
        onChanged();
      } else {
        tacticalReportDataBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
     */
    public Builder addTacticalReportData(
        com.pdsl.reports.proto.TacticalReportData.Builder builderForValue) {
      if (tacticalReportDataBuilder_ == null) {
        ensureTacticalReportDataIsMutable();
        tacticalReportData_.add(builderForValue.build());
        onChanged();
      } else {
        tacticalReportDataBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
     */
    public Builder addTacticalReportData(
        int index, com.pdsl.reports.proto.TacticalReportData.Builder builderForValue) {
      if (tacticalReportDataBuilder_ == null) {
        ensureTacticalReportDataIsMutable();
        tacticalReportData_.add(index, builderForValue.build());
        onChanged();
      } else {
        tacticalReportDataBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
     */
    public Builder addAllTacticalReportData(
        java.lang.Iterable<? extends com.pdsl.reports.proto.TacticalReportData> values) {
      if (tacticalReportDataBuilder_ == null) {
        ensureTacticalReportDataIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, tacticalReportData_);
        onChanged();
      } else {
        tacticalReportDataBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
     */
    public Builder clearTacticalReportData() {
      if (tacticalReportDataBuilder_ == null) {
        tacticalReportData_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        tacticalReportDataBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
     */
    public Builder removeTacticalReportData(int index) {
      if (tacticalReportDataBuilder_ == null) {
        ensureTacticalReportDataIsMutable();
        tacticalReportData_.remove(index);
        onChanged();
      } else {
        tacticalReportDataBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
     */
    public com.pdsl.reports.proto.TacticalReportData.Builder getTacticalReportDataBuilder(
        int index) {
      return getTacticalReportDataFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
     */
    public com.pdsl.reports.proto.TacticalReportDataOrBuilder getTacticalReportDataOrBuilder(
        int index) {
      if (tacticalReportDataBuilder_ == null) {
        return tacticalReportData_.get(index);  } else {
        return tacticalReportDataBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
     */
    public java.util.List<? extends com.pdsl.reports.proto.TacticalReportDataOrBuilder> 
         getTacticalReportDataOrBuilderList() {
      if (tacticalReportDataBuilder_ != null) {
        return tacticalReportDataBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(tacticalReportData_);
      }
    }
    /**
     * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
     */
    public com.pdsl.reports.proto.TacticalReportData.Builder addTacticalReportDataBuilder() {
      return getTacticalReportDataFieldBuilder().addBuilder(
          com.pdsl.reports.proto.TacticalReportData.getDefaultInstance());
    }
    /**
     * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
     */
    public com.pdsl.reports.proto.TacticalReportData.Builder addTacticalReportDataBuilder(
        int index) {
      return getTacticalReportDataFieldBuilder().addBuilder(
          index, com.pdsl.reports.proto.TacticalReportData.getDefaultInstance());
    }
    /**
     * <code>repeated .com.pdsl.reports.proto.TacticalReportData tactical_report_data = 2;</code>
     */
    public java.util.List<com.pdsl.reports.proto.TacticalReportData.Builder> 
         getTacticalReportDataBuilderList() {
      return getTacticalReportDataFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.pdsl.reports.proto.TacticalReportData, com.pdsl.reports.proto.TacticalReportData.Builder, com.pdsl.reports.proto.TacticalReportDataOrBuilder> 
        getTacticalReportDataFieldBuilder() {
      if (tacticalReportDataBuilder_ == null) {
        tacticalReportDataBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.pdsl.reports.proto.TacticalReportData, com.pdsl.reports.proto.TacticalReportData.Builder, com.pdsl.reports.proto.TacticalReportDataOrBuilder>(
                tacticalReportData_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        tacticalReportData_ = null;
      }
      return tacticalReportDataBuilder_;
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


    // @@protoc_insertion_point(builder_scope:com.pdsl.reports.proto.OperationalReportData)
  }

  // @@protoc_insertion_point(class_scope:com.pdsl.reports.proto.OperationalReportData)
  private static final com.pdsl.reports.proto.OperationalReportData DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.pdsl.reports.proto.OperationalReportData();
  }

  public static com.pdsl.reports.proto.OperationalReportData getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<OperationalReportData>
      PARSER = new com.google.protobuf.AbstractParser<OperationalReportData>() {
    @java.lang.Override
    public OperationalReportData parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new OperationalReportData(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<OperationalReportData> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<OperationalReportData> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.pdsl.reports.proto.OperationalReportData getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
